// dllistTest.java
// Unit tests for dllist

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class dllistTest {

    @Test
    public void startsEmptyTest() {
        dllist lst = new dllist();
        assertEquals(true, lst.isEmpty());
    }

    @Test
    public void isEmptyTest() {
    	dllist lst = new dllist();
    	//insert something
    	lst.insert("test1",dllist.position.FIRST);
    	//return false
    	assertEquals(false, lst.isEmpty());
    }

/*
Write a unit test to 
verify that insert at the end of the list 
and getItem work properly 
For example, a single insert at the end of the list 
followed by a getItem 
should return the item inserted.
 Also, two inserts at the end of the list
 followed by a getItem should
return the second item inserted.*/
	@Test
	public void getItemTest() {
		dllist lst = new dllist();
		lst.insert("test2", dllist.position.LAST);
		lst.insert("test3",dllist.position.LAST);
		assertEquals("test3", lst.getItem());
	}
/*
Write more unit tests that test insert putting new items at the front of the list. 
For example, inserting two elements at the front of the list 
followed by getItem should return the second item*/

	@Test
	public void insertAtFirstTest() {
		dllist lst = new dllist();
		lst.insert("test4", dllist.position.FIRST);
		lst.insert("test5", dllist.position.FIRST);
		assertEquals("test5", lst.getItem());		
	}

	/*Write unit tests for setPosition where setPosition moves to the beginning or end of the list. You
should have a unit test that calls insert two times at the end of the list, then setPosition to the
beginning of the list, then call getItem. This should return the first item inserted. */

	@Test
	public void setPositionTest() {
		dllist lst = new dllist();
		lst.insert("test6", dllist.position.LAST);
		lst.insert("test7", dllist.position.LAST);
		lst.setPosition(dllist.position.FIRST);
		assertEquals("test6", lst.getItem());
	}

/*Also write a unit test that inserts twice at the beginning of the list, then calls setPosition to move to the end
of the list. Then getItem should return the first item inserted.*/

	@Test
	public void setPositionTestTwo() {
		dllist lst = new dllist();
		lst.insert("test6", dllist.position.FIRST);
		lst.insert("test7", dllist.position.FIRST);
		lst.setPosition(dllist.position.LAST);
		assertEquals("test6", lst.getItem());
	}

/*Write a unit test that builds up a list using insert. Insert strings "A", "B", and "C" at the end of
the list. Then call insert to insert "D" immediately before the current position ("C"). Then move
to the end of the list with setPosition, and verify that getItem returns "C". */

	@Test
	public void fiveElementsTestOne(){
		dllist lst = new dllist();
		lst.insert("A", dllist.position.LAST);
		lst.insert("B", dllist.position.LAST);
		lst.insert("C", dllist.position.LAST);
		lst.insert("D", dllist.position.PREVIOUS);
		lst.setPosition(dllist.position.LAST);
		assertEquals("C", lst.getItem());
	}
/*Write a unit test that
inserts "A", "B", and "C" at the beginning of the list, the inserts "D" immediately after the
current position ("C"). Move to the beginning of the list with setPosition and verify that getItem
returns "C"*/

	@Test
	public void fiveElementsTestTwo(){
		dllist lst = new dllist();
		lst.insert("A", dllist.position.FIRST);
		lst.insert("B", dllist.position.FIRST);
		lst.insert("C", dllist.position.FIRST);
		lst.insert("D", dllist.position.FOLLOWING);
		lst.setPosition(dllist.position.FIRST);
		assertEquals("C", lst.getItem());
	}

/*Invent a unit test that uses insert and setPosition to create a list of 5 elements in a random order
you specify, then uses setPosition to navigate to each position in the list using PREVIOUS and
FOLLOWING and verifies that getItem returns the correct element in each position.
*/

/*	@Test
	public void insertBeforeTest(){
		dllist lst = new dllist();
		lst.insert("test1", dllist.position.FIRST);
		lst.insert("test2", dllist.position.FOLLOWING);
		lst.insert("test3", dllist.position.PREVIOUS);
		lst.setPosition(dllist.position.FIRST);
		assertEquals("test2", lst.getItem());
		lst.setPosition(dllist.position.LAST);
		assertEquals("test1", lst.getItem());
}*/

	@Test
	public void insertAfterTest(){
		dllist lst = new dllist();
		lst.insert("test1", dllist.position.FIRST);
		lst.insert("test2", dllist.position.FOLLOWING);
		lst.insert("test3", dllist.position.FOLLOWING);
		lst.setPosition(dllist.position.FIRST);
		assertEquals("test1", lst.getItem());
	}

/*	@Test
	public void insertAtLastPositionTest(){
		dllist lst = new dllist();
		lst.insert("test1", dllist.position.FIRST);
		lst.insert("test2", dllist.position.LAST);
		lst.insert("test3", dllist.position.LAST);
		lst.setPosition(dllist.position.FIRST);
		assertEquals("test1", lst.getItem());
		lst.setPosition(dllist.position.LAST);
		assertEquals("test3", dllist.position.LAST);

	}*/

/*Write a unit test similar to the previous one but that calls getPosition at each position to verify
the position is correct instead of verifying the item in that position.*/

/*	@Test
	public void getPositionTest(){
		dllist lst = new dllist();
		lst.insert("test1", dllist.position.FIRST);
		lst.getPosition();
		assertEquals("test1", dllist.position.FIRST);
	}
*/
/*Come up a with a plan for testing delete. Write unit tests following your plan, alternating
writing unit tests and implementing the delete functionality.
*/
	@Test
	public void deleteTest() {
		dllist lst = new dllist();
		lst.insert("a", dllist.position.FIRST);
		lst.insert("b", dllist.position.FOLLOWING);
		lst.insert("c", dllist.position.FOLLOWING);
		lst.delete();
		assertEquals("b", lst.getItem());

	}

/*Write unit tests to cover error cases and verify that your implementation handles error cases
properly.*/

	@Test(expected=Exception.class)
	public void errorCaseIsEmptyTest(){
		dllist lst = new dllist();
    	lst.getItem();
	}

	@Test(expected=Exception.class)
	public void errorCaseGetPositionTest(){
		dllist lst = new dllist();
    	lst.getPosition();
	}

	@Test(expected=Exception.class)
	public void errorCaseDeleteEmptyTest(){
		dllist lst = new dllist();
    	lst.delete();
    }

}