package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class export extends Syntax {
    public static final export export = new export();
    public static final export module_export;

    static {
        export export2 = new export();
        module_export = export2;
        export2.setName("module-export");
        export2.setName("export");
    }

    /* JADX INFO: finally extract failed */
    public boolean scanForDefinitions(Pair st, Vector forms, ScopeExp defs, Translator tr) {
        Object list = st.getCdr();
        Object savePos = tr.pushPositionOf(st);
        try {
            if (defs instanceof ModuleExp) {
                ((ModuleExp) defs).setFlag(16384);
                SyntaxForm restSyntax = null;
                while (list != LList.Empty) {
                    tr.pushPositionOf(list);
                    while (list instanceof SyntaxForm) {
                        restSyntax = (SyntaxForm) list;
                        list = restSyntax.getDatum();
                    }
                    SyntaxForm syntaxForm = restSyntax;
                    if (list instanceof Pair) {
                        Pair st2 = (Pair) list;
                        Object symbol = st2.getCar();
                        while (symbol instanceof SyntaxForm) {
                            symbol = ((SyntaxForm) symbol).getDatum();
                        }
                        if (symbol instanceof String) {
                            String str = (String) symbol;
                            if (str.startsWith("namespace:")) {
                                tr.error('w', "'namespace:' prefix ignored");
                                symbol = str.substring(10).intern();
                            }
                        }
                        if ((symbol instanceof String) || (symbol instanceof Symbol)) {
                            Declaration decl = defs.getNoDefine(symbol);
                            if (decl.getFlag(512)) {
                                Translator.setLine(decl, (Object) st2);
                            }
                            decl.setFlag(1024);
                            list = st2.getCdr();
                        }
                    }
                    tr.error('e', "invalid syntax in '" + getName() + '\'');
                    tr.popPositionOf(savePos);
                    return false;
                }
                tr.popPositionOf(savePos);
                return true;
            }
            tr.error('e', "'" + getName() + "' not at module level");
            tr.popPositionOf(savePos);
            return true;
        } catch (Throwable th) {
            tr.popPositionOf(savePos);
            throw th;
        }
    }

    public Expression rewriteForm(Pair form, Translator tr) {
        return null;
    }
}
