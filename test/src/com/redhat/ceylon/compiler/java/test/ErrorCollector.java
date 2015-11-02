/*
 * Copyright Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the authors tag. All rights reserved.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU General Public License version 2.
 * 
 * This particular file is subject to the "Classpath" exception as provided in the 
 * LICENSE file that accompanied this code.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License,
 * along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package com.redhat.ceylon.compiler.java.test;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.FileObject;

import com.redhat.ceylon.compiler.java.test.CompilerError.Classification;

/**
 * A {@link DiagnosticListener} which collects the {@link Diagnostic}s 
 * generated during compilation
 * @author tom
 */
public class ErrorCollector implements DiagnosticListener<FileObject> {
    
    private final TreeSet<CompilerError> actualErrors = new TreeSet<CompilerError>();

    static Classification classifyDiagnostic(Diagnostic<? extends FileObject> diagnostic) {
        String code = diagnostic.getCode();
        if (code != null && code.startsWith("compiler.err.ceylon.codegen.exception")) {
            return Classification.CRASH;
        } else if (code != null && code.startsWith("compiler.err.ceylon.codegen.erroneous")) {
            return Classification.BACKEND;
        } else if (code != null && (code.startsWith("compiler.err.ceylon")
                    || code.startsWith("compiler.warn.ceylon"))) {
            return Classification.FRONTEND;
        }
        return Classification.BACKEND;
    }
    
    @Override
    public void report(Diagnostic<? extends FileObject> diagnostic) {
        if(diagnostic.getSource() != null)
            System.err.print(diagnostic.getSource().getName() + ":");
        if(diagnostic.getLineNumber() != -1)
            System.err.print(diagnostic.getLineNumber() + ":");
        System.err.println(diagnostic.getKind().toString() + ":" 
                + diagnostic.getMessage(null));
        
        actualErrors.add(new CompilerError(diagnostic.getKind(),
                diagnostic.getSource() != null ? diagnostic.getSource().getName() : null,
                diagnostic.getLineNumber(),
                diagnostic.getMessage(Locale.getDefault()),
                classifyDiagnostic(diagnostic)));
    }
    
    public Set<CompilerError> getAll() {
        return get(EnumSet.allOf(Diagnostic.Kind.class));
    }
    
    public TreeSet<CompilerError> get(Diagnostic.Kind... kinds) {
        EnumSet<Diagnostic.Kind> s = EnumSet.noneOf(Diagnostic.Kind.class);
        for (Diagnostic.Kind kind : kinds) {
            s.add(kind);
        }
        return get(s);
    }
    
    public TreeSet<CompilerError> get(EnumSet<Diagnostic.Kind> kinds) {
        TreeSet<CompilerError> result = new TreeSet<CompilerError>();
        for (CompilerError diagnostic : actualErrors) {
            if (kinds.contains(diagnostic.kind)) {
                result.add(diagnostic);
            }
        }
        return result;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (actualErrors.isEmpty()) {
            return "Unknown errors (was a stacktrace dumped?)";
        }
        for (CompilerError e : actualErrors) {
            sb.append(e).append(System.lineSeparator());
        }
        return sb.substring(0, sb.length()-System.lineSeparator().length());
    }
    
    public String getAssertionFailureMessage() {
        return "Compilation failed" + System.lineSeparator() + this;
    }
    
    public int getNumBackendErrors() {
        int num = 0;
        for (CompilerError err : actualErrors) {
            if ((err.classification == Classification.BACKEND && err.kind != Diagnostic.Kind.NOTE)
                    || err.classification == Classification.CRASH) {
                num++;
            }
        }
        return num;
    }
    
}