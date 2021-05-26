package exercise.android.reemh.todo_items;

import org.junit.Assert;
import org.junit.Test;

public class TodoItemsHolderImplTest {
  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    Assert.assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_addingTodoItem_then_callingListShouldHaveItemWithTheCorrectDescription(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(contex);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    Assert.assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).getDescription());
  }

  @Test
  public void when_removeTodoItem_then_callingListShouldNotHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));
    // verify
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_removeTodoItem_then_callingListShouldNotHaveItemWithTheDescription(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("a");
    holderUnderTest.addNewInProgressItem("b");
    holderUnderTest.addNewInProgressItem("c");
    holderUnderTest.addNewInProgressItem("d");

    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));
    // verify
    for (TodoItem item : holderUnderTest.getCurrentItems()){
      Assert.assertNotEquals("d",item.getDescription());
    }

  }

  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItemInFront(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("homework");
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    Assert.assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).getDescription());
  }

  @Test
  public void when_markTodoItem_asDone_callingListShouldNotHaveThisItemInFront(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("homework");

    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

    // verify
    Assert.assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).getDescription());
  }

  @Test
  public void when_markTodoItem_asInProgress_callingListShouldHaveThisItemInFront(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("homework");

    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(1));
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(1));

    // verify
    Assert.assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).getDescription());
  }

  @Test
  public void when_markTodoItem_asInProgress_itemStateShouldBe_InProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("homework");

    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(1));
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(1));

    // verify
    Assert.assertFalse(holderUnderTest.getCurrentItems().get(0).isDone());
  }

  @Test
  public void when_markTodoItem_asDone_itemStateShouldBe_Done(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("homework");

    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(1));
    // verify
    Assert.assertTrue(holderUnderTest.getCurrentItems().get(1).isDone());
  }

  @Test
  public void complexCheckTheListIsOrganizeFirstInProgressThenDoneItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("why");
    holderUnderTest.addNewInProgressItem("so");
    holderUnderTest.addNewInProgressItem("many");
    holderUnderTest.addNewInProgressItem("tests");
    holderUnderTest.addNewInProgressItem("i");
    holderUnderTest.addNewInProgressItem("hate");
    holderUnderTest.addNewInProgressItem("this");


    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    // verify
    Assert.assertEquals("tests", holderUnderTest.getCurrentItems().get(0).getDescription());
    Assert.assertEquals("many", holderUnderTest.getCurrentItems().get(1).getDescription());
    Assert.assertEquals("so", holderUnderTest.getCurrentItems().get(2).getDescription());
    Assert.assertEquals("why", holderUnderTest.getCurrentItems().get(3).getDescription());
  }

  @Test
  public void deletingItemInTheMiddleOfTheListDontChangeTheOrder(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(context);
    Assert.assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("why");
    holderUnderTest.addNewInProgressItem("so");
    holderUnderTest.addNewInProgressItem("many");
    holderUnderTest.addNewInProgressItem("tests");
    holderUnderTest.addNewInProgressItem("i");
    holderUnderTest.addNewInProgressItem("hate");
    holderUnderTest.addNewInProgressItem("this");


    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(3));

    // verify
    Assert.assertEquals("this", holderUnderTest.getCurrentItems().get(0).getDescription());
    Assert.assertEquals("hate", holderUnderTest.getCurrentItems().get(1).getDescription());
    Assert.assertEquals("i", holderUnderTest.getCurrentItems().get(2).getDescription());
    Assert.assertEquals("many", holderUnderTest.getCurrentItems().get(3).getDescription());
    Assert.assertEquals("so", holderUnderTest.getCurrentItems().get(4).getDescription());
    Assert.assertEquals("why", holderUnderTest.getCurrentItems().get(5).getDescription());
  }

}