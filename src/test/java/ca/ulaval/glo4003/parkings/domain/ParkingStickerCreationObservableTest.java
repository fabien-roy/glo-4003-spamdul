package ca.ulaval.glo4003.parkings.domain;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerCreationObservableTest {

  @Mock private ParkingStickerCreationObserver firstObserver;
  @Mock private ParkingStickerCreationObserver secondObserver;

  private ParkingStickerCreationObservable parkingStickerCreationObservable;

  private final ParkingSticker parkingSticker = aParkingSticker().build();

  @Before
  public void setUp() {
    parkingStickerCreationObservable = new ParkingStickerCreationObservable();

    parkingStickerCreationObservable.register(firstObserver);
    parkingStickerCreationObservable.register(secondObserver);
  }

  @Test
  public void whenNotifyingParkingStickerCreation_thenMakeEachObserverListen() {
    parkingStickerCreationObservable.notifyParkingStickerCreated(parkingSticker);

    Mockito.verify(firstObserver).listenParkingStickerCreated(parkingSticker);
    Mockito.verify(secondObserver).listenParkingStickerCreated(parkingSticker);
  }
}
