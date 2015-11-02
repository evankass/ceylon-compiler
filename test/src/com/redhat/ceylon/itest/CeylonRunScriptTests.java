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
package com.redhat.ceylon.itest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.redhat.ceylon.common.tools.CeylonTool;
import com.redhat.ceylon.itest.AntBasedTests.AntResult;

public class CeylonRunScriptTests extends AntBasedTests {

    public CeylonRunScriptTests() throws Exception {
        super("test/src/com/redhat/ceylon/itest/ceylon-run-script.xml");
    }
    
    private void assertExecutedOk(AntResult result) {
        Assert.assertEquals(0, result.getStatusCode());
        assertNotContainsMatch(result.getStdout(), "\\[exec\\] Result: [0-9]+");
    }

    @Test
    public void testVersion() throws Exception {
        AntResult result = ant("version");
        Assert.assertEquals(1, result.getStatusCode());
        assertContains(result.getStdout(), "[exec] ceylon run: ");
        assertContains(result.getStdout(), "exec returned: " + CeylonTool.SC_ARGS);
    }
    
    @Test
    public void testHelp() throws Exception {
        AntResult result = ant("help");
        Assert.assertEquals(CeylonTool.SC_OK, result.getStatusCode());
        assertContains(result.getStdout(), "[exec] DESCRIPTION");
    }
    
    @Test
    public void testH() throws Exception {
        AntResult result = ant("h");
        Assert.assertEquals(CeylonTool.SC_OK, result.getStatusCode());
        assertContains(result.getStdout(), "[exec] DESCRIPTION");
    }
    
    @Test
    public void testExecHelloMethodCompiled() throws Exception {
        AntResult result = ant("exec-hello-compiled");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Hello, world");
    }
    
    @Test
    public void testExecDefaultCompiled() throws Exception {
        AntResult result = ant("exec-default-compiled");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Hello, world");
    }
    
    @Test
    public void testExecDefaultCompiledFunc() throws Exception {
        AntResult result = ant("exec-default-compiled-func");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Hello, world");
    }
    
    @Test
    public void testExecDefaultCompiledArg() throws Exception {
        AntResult result = ant("exec-default-compiled-arg");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Hello, Tako");
    }
    
    @Test
    public void testExecGoodbyeClassCompiled() throws Exception {
        AntResult result = ant("exec-goodbye-compiled");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Goodbye cruel world");
    }
    
    @Test
    @Ignore("Module descriptor doesn't support 'run' yet")
    public void testExecFooModuleCompiled() throws Exception {
        AntResult result = ant("exec-foo-compiled");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Hello, world");
    }
    
    @Test
    @Ignore("ceylon doesn't support compilation yet")
    public void testExecHelloMethodFromSource() throws Exception {
        AntResult result = ant("exec-hello-source");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Hello, world");
    }
    
    @Test
    @Ignore("ceylon doesn't support compilation yet")
    public void testExecGoodbyeClassFromSource() throws Exception {
        AntResult result = ant("exec-goodbye-source");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Goodbye cruel world");
    }
    
    @Test
    @Ignore("ceylon doesn't support compilation yet")
    // and... @Ignore("Module descriptor doesn't support 'run' yet")
    public void testExecFooModuleFromSource() throws Exception {
        AntResult result = ant("exec-foo-compiled");
        Assert.assertEquals(0, result.getStatusCode());
        assertContains(result.getStdout(), "Hello, world");
    }
    
}
