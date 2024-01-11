package opp;

import com.fasterxml.jackson.databind.ObjectMapper;
import opp.domain.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddCar() throws Exception {
        Car car = new Car(1L, "Toyota", "Camry", 12345L);

        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk());

        // Add additional assertions if needed
    }

    @Test
    public void testGetCarById() throws Exception {
        Long carId = 2L;

        mockMvc.perform(MockMvcRequestBuilders.get("/car/{id}", carId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(carId.intValue())));

        // Add additional assertions if needed
    }

    @Test
    public void testRemoveCar() throws Exception {
        Long carId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/remove/{id}", carId))
                .andExpect(status().isOk());

        // Add additional assertions if needed
    }

    @Test
    public void testModifyCar() throws Exception {
        Car car = new Car(2L, "Honda", "Accord", 67890L);

        mockMvc.perform(MockMvcRequestBuilders.post("/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk());

        // Add additional assertions if needed
    }

    // Add more tests for error cases, invalid requests, etc.

}
