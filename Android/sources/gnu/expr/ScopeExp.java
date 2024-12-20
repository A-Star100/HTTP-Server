package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;

public abstract class ScopeExp extends Expression {
    static int counter;
    Declaration decls;
    protected int frameSize;
    public int id;
    Declaration last;
    public ScopeExp outer;
    private Scope scope;

    public Declaration firstDecl() {
        return this.decls;
    }

    public Scope getVarScope() {
        Scope sc = this.scope;
        if (sc != null) {
            return sc;
        }
        Scope scope2 = new Scope();
        Scope sc2 = scope2;
        this.scope = scope2;
        return sc2;
    }

    public void popScope(CodeAttr code) {
        for (Declaration decl = firstDecl(); decl != null; decl = decl.nextDecl()) {
            decl.var = null;
        }
        code.popScope();
        this.scope = null;
    }

    public void add(Declaration decl) {
        Declaration declaration = this.last;
        if (declaration == null) {
            this.decls = decl;
        } else {
            declaration.next = decl;
        }
        this.last = decl;
        decl.context = this;
    }

    public void add(Declaration prev, Declaration decl) {
        if (prev == null) {
            decl.next = this.decls;
            this.decls = decl;
        } else {
            decl.next = prev.next;
            prev.next = decl;
        }
        if (this.last == prev) {
            this.last = decl;
        }
        decl.context = this;
    }

    public void replaceFollowing(Declaration prev, Declaration newDecl) {
        Declaration oldDecl;
        if (prev == null) {
            oldDecl = this.decls;
            this.decls = newDecl;
        } else {
            oldDecl = prev.next;
            prev.next = newDecl;
        }
        newDecl.next = oldDecl.next;
        if (this.last == oldDecl) {
            this.last = newDecl;
        }
        oldDecl.next = null;
        newDecl.context = this;
    }

    public void remove(Declaration decl) {
        Declaration prev = null;
        Declaration cur = firstDecl();
        while (cur != null) {
            if (cur == decl) {
                remove(prev, decl);
                return;
            } else {
                prev = cur;
                cur = cur.nextDecl();
            }
        }
    }

    public void remove(Declaration prev, Declaration decl) {
        if (prev == null) {
            this.decls = decl.next;
        } else {
            prev.next = decl.next;
        }
        if (this.last == decl) {
            this.last = prev;
        }
    }

    public ScopeExp() {
        int i = counter + 1;
        counter = i;
        this.id = i;
    }

    public LambdaExp currentLambda() {
        for (ScopeExp exp = this; exp != null; exp = exp.outer) {
            if (exp instanceof LambdaExp) {
                return (LambdaExp) exp;
            }
        }
        return null;
    }

    public ScopeExp topLevel() {
        ScopeExp exp = this;
        while (true) {
            ScopeExp outer2 = exp.outer;
            if (outer2 == null || (outer2 instanceof ModuleExp)) {
                return exp;
            }
            exp = outer2;
        }
        return exp;
    }

    public ModuleExp currentModule() {
        for (ScopeExp exp = this; exp != null; exp = exp.outer) {
            if (exp instanceof ModuleExp) {
                return (ModuleExp) exp;
            }
        }
        return null;
    }

    public Declaration lookup(Object sym) {
        if (sym == null) {
            return null;
        }
        for (Declaration decl = firstDecl(); decl != null; decl = decl.nextDecl()) {
            if (sym.equals(decl.symbol)) {
                return decl;
            }
        }
        return null;
    }

    public Declaration lookup(Object sym, Language language, int namespace) {
        for (Declaration decl = firstDecl(); decl != null; decl = decl.nextDecl()) {
            if (sym.equals(decl.symbol) && language.hasNamespace(decl, namespace)) {
                return decl;
            }
        }
        return null;
    }

    public Declaration getNoDefine(Object name) {
        Declaration decl = lookup(name);
        if (decl != null) {
            return decl;
        }
        Declaration decl2 = addDeclaration(name);
        decl2.flags |= 66048;
        return decl2;
    }

    public Declaration getDefine(Object name, char severity, Compilation parser) {
        Declaration decl = lookup(name);
        if (decl == null) {
            return addDeclaration(name);
        }
        if ((decl.flags & 66048) != 0) {
            decl.flags &= -66049;
            return decl;
        }
        Declaration newDecl = addDeclaration(name);
        duplicateDeclarationError(decl, newDecl, parser);
        return newDecl;
    }

    public static void duplicateDeclarationError(Declaration oldDecl, Declaration newDecl, Compilation comp) {
        comp.error('e', newDecl, "duplicate declaration of '", "'");
        comp.error('e', oldDecl, "(this is the previous declaration of '", "')");
    }

    public final Declaration addDeclaration(Object name) {
        Declaration decl = new Declaration(name);
        add(decl);
        return decl;
    }

    public final Declaration addDeclaration(Object name, Type type) {
        Declaration decl = new Declaration(name, type);
        add(decl);
        return decl;
    }

    public final void addDeclaration(Declaration decl) {
        add(decl);
    }

    public int countDecls() {
        int n = 0;
        for (Declaration decl = firstDecl(); decl != null; decl = decl.nextDecl()) {
            n++;
        }
        return n;
    }

    public int countNonDynamicDecls() {
        int n = 0;
        for (Declaration decl = firstDecl(); decl != null; decl = decl.nextDecl()) {
            if (!decl.getFlag(268435456)) {
                n++;
            }
        }
        return n;
    }

    public static int nesting(ScopeExp sc) {
        int n = 0;
        while (sc != null) {
            sc = sc.outer;
            n++;
        }
        return n;
    }

    public boolean nestedIn(ScopeExp outer2) {
        for (ScopeExp sc = this; sc != null; sc = sc.outer) {
            if (sc == outer2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void setIndexes() {
        int i = 0;
        Declaration decl = firstDecl();
        while (decl != null) {
            decl.evalIndex = i;
            decl = decl.nextDecl();
            i++;
        }
        this.frameSize = i;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitScopeExp(this, d);
    }

    public String toString() {
        return getClass().getName() + "#" + this.id;
    }
}
