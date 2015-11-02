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
@noanno
shared class SetOperators() {

    void testSetOperatorsWithSameTypes(Set<Integer> a, Set<Integer> b) {
        variable Set<Integer> x;
        x = a | b;
        x = a & b;
        x = a ~ b;
        x |= a;
        x &= a;
        x ~= a;
    }

    void testSetOperatorsWithDifferentTypes(Set<Integer> a, Set<Float> b) {
        variable Set<Integer|Float> x1 = a | b;
        x1.contains(1);
        x1.contains(1.1);
        for (Integer|Float i in x1) {
            if (is Integer i) {
                Integer s = i + 0;
            } else {
                Float s = i + 0;
            }
        }
        variable Set<Integer&Float> x2 = a & b;
        x1.contains(1);
        x1.contains(1.1);
        variable Set<Integer> x4 = a ~ b;
        x4 &= b;
        x4 ~= b;
        x4.contains(1);
        for (Integer i in x4) {
            Integer s = i + 0;
        }
    }
    
    void testSetOperatorsAndInvocationOnResult(Set<Integer> a, Set<Float> b) {
        (a | a).contains(1);
        (a & a).contains(1);
        (a ~ a).contains(1);
        
        (a | b).contains(3.14);
        (a & b).contains(3.14);
        (a ~ b).contains(3.14);
    }
    
    void testSetOperatorsPrecedence(Set<Integer> a, Set<Integer> b, Set<Integer> c) {
        variable Set<Integer> x1 = a | b & c;
        variable Set<Integer> x2 = b & c | a;
    }
    
    void testSetOperatorsWithErasedTypes(Set<String> setOfString, Set<Integer | Float> setOfUnionType, Set<Summable<Integer> & Empty> setOfIntersectionType, Set<Nothing> setOfNothing) {
        variable Set<Integer | Float | String> x1 = setOfUnionType | setOfString;
        variable Set<Integer | Float & String> x2 = setOfUnionType & setOfString;
        variable Set<Integer | Float> x4 = setOfUnionType ~ setOfString;
        
        variable Set<Summable<Integer> & Empty | String> y1 = setOfIntersectionType | setOfString;
        variable Set<Summable<Integer> & Empty & String> y2 = setOfIntersectionType & setOfString;
        variable Set<Summable<Integer> & Empty> y4 = setOfIntersectionType ~ setOfString;
        
        variable Set<Nothing | String> z1 = setOfNothing | setOfString;
        variable Set<Nothing & String> z2 = setOfNothing & setOfString;
        variable Set<Nothing> z4 = setOfNothing ~ setOfString;
    }
    
    void m3(Set<Integer> a, Set<Nothing> b) {
        Set<Integer> s1 = a | b;
        Set<Nothing> s2 = a & b;
        Set<Integer> s4 = a ~ b;
        variable Set<Integer> sync;
        sync = a | b;
        sync = a & b;
        sync = a ~ b;
        sync |= a;
        sync &= a;
        sync ~= a;
    }
    
    void m4<T>(Set<Object> a, Set<T> b, T t) 
            given T satisfies Object{
        Set<Object> s1 = a | b;
        Set<T> s2 = a & b;
        s2.contains(t);
        Set<Object> s4 = a ~ b;
        variable Set<Object> sync;
        sync = a | b;
        sync = a & b;
        sync = a ~ b;
        sync |= a;
        sync &= a;
        sync ~= a;
    }
}
@noanno
class SetOperatorsSuper(a, b) {
    shared variable Set<Integer> a;
    shared variable Set<Integer> b; 
    shared variable Set<Integer> sync = a;
    
    shared default void m() {
        sync = a | b;
        sync = a & b;
        sync = a ~ b;
        sync |= a;
        sync &= a;
        sync ~= a;
    }
}
@noanno
class SetOperatorsSub(Set<Integer> a, Set<Nothing> b) extends SetOperatorsSuper(a, b) {
    shared actual void m() {
        super.sync = super.a | super.b;
        super.sync = super.a & super.b;
        super.sync = super.a ~ super.b;
        super.sync |= super.a;
        super.sync &= super.a;
        super.sync ~= super.a;
    }
}