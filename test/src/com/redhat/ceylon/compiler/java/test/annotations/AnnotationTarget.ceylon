
@nomodel
final annotation class AnnotationTarget() satisfies OptionalAnnotation<AnnotationTarget,Annotated> {}
@nomodel
annotation AnnotationTarget annotationTarget() => AnnotationTarget();
@nomodel
final annotation class AnnotationTarget2() satisfies OptionalAnnotation<AnnotationTarget2,Annotated> {}
@nomodel
annotation AnnotationTarget2 annotationTarget2() => AnnotationTarget2();

@nomodel
annotationTarget
class AnnotationTargetClass(annotationTarget String parameter) {
    
    annotationTarget
    shared Boolean attribute = true;
    
    annotationTarget
    shared variable Boolean varAttribute = true;
    
    annotationTarget
    shared Boolean getter => true;
    
    annotationTarget2
    assign getter {}
    
    annotationTarget
    shared object obj {}
    
    annotationTarget
    void method(annotationTarget String parameter) {
        annotationTarget
        assert(true);
        
        annotationTarget
        class LocalClass(annotationTarget String parameter) {
        }
    
        annotationTarget
        interface LocalInterface {
        }
    }
    
    annotationTarget
    class InnerClass(annotationTarget String parameter) {
    }
    
    annotationTarget
    interface InnerInterface {
    }
}

@nomodel
annotationTarget
alias AnnotationTargetAlias => AnnotationTargetClass;

@nomodel
annotationTarget
class AnnotationTargetClassAlias(annotationTarget String parameter) => AnnotationTargetClass(parameter);

@nomodel
class AnnotationTargetOuter() {
    annotationTarget
    class InnerAlias(annotationTarget String parameter) => AnnotationTargetClass(parameter);
}

@nomodel
annotationTarget
interface AnnotationTargetInterface {
    
    annotationTarget
    shared formal void formalMethod(annotationTarget String parameter);
    
    annotationTarget
    shared default void defaultMethod(annotationTarget String parameter) {}
    
    annotationTarget
    shared void sharedMethod(annotationTarget String parameter) {
        // Capture these
        method(attribute);
        method(getter);
    }
    
    annotationTarget
    void method(annotationTarget String parameter) {}
    
    annotationTarget
    String attribute => "";
    
    annotationTarget
    shared String sharedAttribute => "";
    
    annotationTarget
    String getter {
        return "";
    }
    annotationTarget2
    assign getter {}
    
    annotationTarget
    class InnerClass(annotationTarget String parameter) {
    }
    
    annotationTarget
    interface InnerInterface {
    }
}

@nomodel
annotationTarget
interface AnnotationTargetInterfaceAlias => AnnotationTargetInterface;

@nomodel
annotationTarget
void annotationTargetMethod(annotationTarget String parameter) {
}

@nomodel
annotationTarget
Boolean annotationTargetValue = true;

@nomodel
annotationTarget
Boolean annotationTargetGetterSetter { 
    return true; 
}
annotationTarget2
assign annotationTargetGetterSetter {}

@nomodel
annotationTarget
object annotationTargetObject {}
