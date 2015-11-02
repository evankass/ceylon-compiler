package com.redhat.ceylon.compiler.java.codegen;

import java.util.HashMap;
import java.util.Map;

import com.redhat.ceylon.compiler.typechecker.tree.Tree;
import com.redhat.ceylon.compiler.typechecker.tree.Visitor;
import com.redhat.ceylon.model.typechecker.model.ControlBlock;

public class LabelVisitor extends Visitor {

    private int id = 0;
    
    private Tree.ControlClause loop;
    

    private Map<ControlBlock, Integer> loopMap = new HashMap<>();

    private Map<Tree.SwitchClause, Integer> switchMap = new HashMap<>();
    
    /** Returns a unique identifier (unique within the compilation unit)
     * for the control block if it's associated with a {@code while} or 
     * {@code for} loop, null otherwise.
     */
    public Integer getLoopId(ControlBlock controlBlock) {
        return loopMap.get(controlBlock);
    }
    
    public Integer getSwitchId(Tree.SwitchClause switchClause) {
        return switchMap.get(switchClause);
    }
    
    @Override
    public void visit(Tree.WhileClause that) {
        Tree.ControlClause prev = loop;
        loop = that;
        loopMap.put(that.getControlBlock(), id++);
        super.visit(that);
        loop = prev;
    }
    
    @Override
    public void visit(Tree.ForClause that) {
        Tree.ControlClause prev = loop;
        loop = that;
        loopMap.put(that.getControlBlock(), id++);
        super.visit(that);
        loop = prev;
    }
    
    @Override
    public void visit(Tree.SwitchClause that) {
        switchMap.put(that, id++);
    }
    
}
