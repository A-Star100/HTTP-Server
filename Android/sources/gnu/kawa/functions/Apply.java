package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

public class Apply extends ProcedureN {
    ApplyToArgs applyToArgs;

    public Apply(String name, ApplyToArgs applyToArgs2) {
        super(name);
        this.applyToArgs = applyToArgs2;
    }

    public static Object[] getArguments(Object[] args, int skip, Procedure proc) {
        int last_count;
        int count = args.length;
        if (count >= skip + 1) {
            Object last = args[count - 1];
            if (last instanceof Object[]) {
                Object[] last_arr = (Object[]) last;
                if (count == 2) {
                    return last_arr;
                }
                last_count = last_arr.length;
            } else if ((last instanceof Sequence) != 0) {
                last_count = ((Sequence) last).size();
            } else {
                last_count = -1;
            }
            if (last_count >= 0) {
                Object[] proc_args = new Object[(((count - skip) - 1) + last_count)];
                int i = 0;
                while (i < (count - skip) - 1) {
                    proc_args[i] = args[i + skip];
                    i++;
                }
                if (last instanceof Object[]) {
                    System.arraycopy((Object[]) last, 0, proc_args, i, last_count);
                } else {
                    while (last instanceof Pair) {
                        Pair pair = (Pair) last;
                        proc_args[i] = pair.getCar();
                        last = pair.getCdr();
                        last_count--;
                        i++;
                    }
                    if (last_count > 0) {
                        Sequence last_seq = (Sequence) last;
                        int j = 0;
                        while (j < last_count) {
                            proc_args[i] = last_seq.get(j);
                            j++;
                            i++;
                        }
                    }
                }
                return proc_args;
            }
            throw new WrongType(proc, count, last, "sequence or array");
        }
        throw new WrongArguments("apply", 2, "(apply proc [args] args) [count:" + count + " skip:" + skip + "]");
    }

    public Object applyN(Object[] args) throws Throwable {
        return this.applyToArgs.applyN(getArguments(args, 0, this));
    }

    public void apply(CallContext ctx) throws Throwable {
        this.applyToArgs.checkN(getArguments(ctx.getArgs(), 0, this), ctx);
    }
}
