package ca.ulaval.glo4003.accesspasses.domain;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassCreationObservableTest {
  @Mock private AccessPassCreationObserver firstObserver;
  @Mock private AccessPassCreationObserver secondObserver;

  private AccessPassCreationObservable accessPassCreationObservable;

  private final AccessPass accessPass = anAccessPass().build();

  @Before
  public void setUp() {
    accessPassCreationObservable = new AccessPassCreationObservable();

    accessPassCreationObservable.register(firstObserver);
    accessPassCreationObservable.register(secondObserver);
  }

  @Test
  public void whenNotifyAccessPassCreation_thenMakeEachObserverListen() {
    accessPassCreationObservable.notifyAccessPassCreated(accessPass);

    Mockito.verify(firstObserver).listenAccessPassCreated(accessPass);
    Mockito.verify(secondObserver).listenAccessPassCreated(accessPass);
  }
}
