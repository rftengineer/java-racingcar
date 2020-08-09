package step3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step3.generator.RandomGenerator;
import step3.model.Car;

import static org.assertj.core.api.Assertions.assertThat;
import static step3.generator.RandomGenerator.generateValue;

@DisplayName("Step3 - 자동차 Object에 대한 Test")
public class CarTest {
    private Car challenger;

    @Test
    @DisplayName("Car Construction 정상 작동여부확인")
    void new_Car_object_creation() {
        challenger = new Car();
        assertThat(challenger.showDistance()).isEqualTo(1);
    }

    @Test
    @DisplayName("Car 객체에 정의된 Method로 자동차가 이동했는지 확인")
    void increse_Car_Distance_with_Method() {
        challenger = new Car();
        challenger.increaseDistance();
        assertThat(challenger.showDistance()).isEqualTo(2);
    }

    @Test
    void increate_Car_Distance_with_Randomize() {
        challenger = new Car();
        for (int i = 0; i <= 10; i++) {
            challenger.accelerateCar();
        }
        assertThat(challenger.showDistance()).isGreaterThan(2);
    }
}
