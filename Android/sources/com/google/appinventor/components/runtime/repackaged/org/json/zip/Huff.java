package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import com.google.appinventor.components.runtime.repackaged.org.json.JSONException;

public class Huff implements None, PostMortem {
    private final int domain;
    private final Symbol[] symbols;
    private Symbol table;
    private boolean upToDate = false;
    private int width;

    private static class Symbol implements PostMortem {
        public Symbol back = null;
        public final int integer;
        public Symbol next = null;
        public Symbol one = null;
        public long weight = 0;
        public Symbol zero = null;

        public Symbol(int integer2) {
            this.integer = integer2;
        }

        public boolean postMortem(PostMortem pm) {
            boolean result = true;
            Symbol that = (Symbol) pm;
            if (this.integer != that.integer || this.weight != that.weight) {
                return false;
            }
            boolean z = true;
            boolean z2 = this.back != null;
            if (that.back == null) {
                z = false;
            }
            if (z2 != z) {
                return false;
            }
            Symbol zero2 = this.zero;
            Symbol one2 = this.one;
            if (zero2 != null) {
                result = zero2.postMortem(that.zero);
            } else if (that.zero != null) {
                return false;
            }
            if (one2 != null) {
                return one2.postMortem(that.one);
            }
            if (that.one != null) {
                return false;
            }
            return result;
        }
    }

    public Huff(int domain2) {
        this.domain = domain2;
        int length = (domain2 * 2) - 1;
        this.symbols = new Symbol[length];
        for (int i = 0; i < domain2; i++) {
            this.symbols[i] = new Symbol(i);
        }
        for (int i2 = domain2; i2 < length; i2++) {
            this.symbols[i2] = new Symbol(-1);
        }
    }

    public void generate() {
        Symbol next;
        Symbol next2;
        if (!this.upToDate) {
            Symbol head = this.symbols[0];
            Symbol previous = head;
            this.table = null;
            head.next = null;
            for (int i = 1; i < this.domain; i++) {
                Symbol symbol = this.symbols[i];
                if (symbol.weight < head.weight) {
                    symbol.next = head;
                    head = symbol;
                } else {
                    if (symbol.weight < previous.weight) {
                        previous = head;
                    }
                    while (true) {
                        next2 = previous.next;
                        if (next2 == null || symbol.weight < next2.weight) {
                            symbol.next = next2;
                            previous.next = symbol;
                            previous = symbol;
                        } else {
                            previous = next2;
                        }
                    }
                    symbol.next = next2;
                    previous.next = symbol;
                    previous = symbol;
                }
            }
            int avail = this.domain;
            Symbol previous2 = head;
            while (true) {
                Symbol first = head;
                Symbol second = first.next;
                head = second.next;
                Symbol symbol2 = this.symbols[avail];
                avail++;
                symbol2.weight = first.weight + second.weight;
                symbol2.zero = first;
                symbol2.one = second;
                symbol2.back = null;
                first.back = symbol2;
                second.back = symbol2;
                if (head == null) {
                    this.table = symbol2;
                    this.upToDate = true;
                    return;
                } else if (symbol2.weight < head.weight) {
                    symbol2.next = head;
                    head = symbol2;
                    previous2 = head;
                } else {
                    while (true) {
                        next = previous2.next;
                        if (next == null || symbol2.weight < next.weight) {
                            symbol2.next = next;
                            previous2.next = symbol2;
                            previous2 = symbol2;
                        } else {
                            previous2 = next;
                        }
                    }
                    symbol2.next = next;
                    previous2.next = symbol2;
                    previous2 = symbol2;
                }
            }
        }
    }

    private boolean postMortem(int integer) {
        int[] bits = new int[this.domain];
        Symbol symbol = this.symbols[integer];
        if (symbol.integer != integer) {
            return false;
        }
        int i = 0;
        while (true) {
            Symbol back = symbol.back;
            if (back != null) {
                if (back.zero == symbol) {
                    bits[i] = 0;
                } else if (back.one != symbol) {
                    return false;
                } else {
                    bits[i] = 1;
                }
                i++;
                symbol = back;
            } else if (symbol != this.table) {
                return false;
            } else {
                this.width = 0;
                Symbol symbol2 = this.table;
                while (symbol2.integer == -1) {
                    i--;
                    symbol2 = bits[i] != 0 ? symbol2.one : symbol2.zero;
                }
                if (symbol2.integer == integer && i == 0) {
                    return true;
                }
                return false;
            }
        }
    }

    public boolean postMortem(PostMortem pm) {
        for (int integer = 0; integer < this.domain; integer++) {
            if (!postMortem(integer)) {
                JSONzip.log("\nBad huff ");
                JSONzip.logchar(integer, integer);
                return false;
            }
        }
        return this.table.postMortem(((Huff) pm).table);
    }

    public int read(BitReader bitreader) throws JSONException {
        try {
            this.width = 0;
            Symbol symbol = this.table;
            while (symbol.integer == -1) {
                this.width++;
                symbol = bitreader.bit() ? symbol.one : symbol.zero;
            }
            tick(symbol.integer);
            return symbol.integer;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }

    public void tick(int value) {
        this.symbols[value].weight++;
        this.upToDate = false;
    }

    public void tick(int from, int to) {
        for (int value = from; value <= to; value++) {
            tick(value);
        }
    }

    private void write(Symbol symbol, BitWriter bitwriter) throws JSONException {
        try {
            Symbol back = symbol.back;
            if (back != null) {
                this.width++;
                write(back, bitwriter);
                if (back.zero == symbol) {
                    bitwriter.zero();
                } else {
                    bitwriter.one();
                }
            }
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }

    public void write(int value, BitWriter bitwriter) throws JSONException {
        this.width = 0;
        write(this.symbols[value], bitwriter);
        tick(value);
    }
}
