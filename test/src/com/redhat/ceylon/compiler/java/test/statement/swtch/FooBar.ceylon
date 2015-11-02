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
interface Common {
    shared formal Object common();
}

interface FooInterface {
    shared formal Object foo();
}

class Foo() satisfies Common & FooInterface {
    shared actual Object common() {
        return false;
    }
    shared actual Object foo() {
        return false;
    }
}
class FooSub() extends Foo() {
    shared void foo2() {}
}

interface BarInterface {
    shared formal Object bar();
}
class Bar() satisfies Common & BarInterface {
    shared actual Object common() {
        return false;
    }
    shared actual Object bar() {
        return false;
    }
}
interface BazInterface {
    shared formal Object baz();
}
class Baz() satisfies Common & BazInterface {
    shared actual Object common() {
        return false;
    }
    shared actual Object baz() {
        return false;
    }
}
