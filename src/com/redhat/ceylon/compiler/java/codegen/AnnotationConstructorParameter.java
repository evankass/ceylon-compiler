package com.redhat.ceylon.compiler.java.codegen;

import com.redhat.ceylon.model.loader.NamingBase.Prefix;
import com.redhat.ceylon.model.typechecker.model.Parameter;

/**
 * A parameter to an annotation constructor, 
 * recording information about its default argument.
 *
 */
public class AnnotationConstructorParameter implements AnnotationFieldName {

    private Parameter parameter;
    
    private AnnotationTerm defaultArgument;

    public AnnotationConstructorParameter() {}
    
    /**
     * The corresponding parameter of the annotation constructor {@code Function}
     * @return
     */
    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public AnnotationTerm getDefaultArgument() {
        return defaultArgument;
    }

    public void setDefaultArgument(AnnotationTerm defaultArgument) {
        this.defaultArgument = defaultArgument;
    }
    
    @Override
    public String toString() {
        return parameter.getName() + (defaultArgument != null ? "=" + defaultArgument : "");
    }

    @Override
    public String getFieldName() {
        return parameter.getName();
    }
    
    @Override
    public Prefix getFieldNamePrefix() {
        return Prefix.$default$;
    }

    @Override
    public Parameter getAnnotationField() {
        return getParameter();
    }
}
