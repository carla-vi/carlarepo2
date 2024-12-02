package es.upm.dit.apsv.transportationorderserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import es.upm.dit.apsv.transportationorderserver.repository.TransportationOrderRepository;
import es.upm.dit.apsv.transportationorderserver.controller.TransportationOrderController;
import es.upm.dit.apsv.transportationorderserver.model.TransportationOrder;



@WebMvcTest(TransportationOrderController.class)
public class TransportationOrderControllerTest {
    @InjectMocks
    private TransportationOrderController business;
    @MockBean
    private TransportationOrderRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetOrders() throws Exception {
        //call GET "/transportationorders"  application/json
        when(repository.findAll()).thenReturn(getAllTestOrders());
        RequestBuilder request = MockMvcRequestBuilders
                .get("/transportationorders")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(20)))
                .andReturn();
    }


    private List<TransportationOrder> getAllTestOrders(){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<TransportationOrder> orders =
               new ArrayList<TransportationOrder>();
        TransportationOrder order = null;
        try(BufferedReader br = new BufferedReader(new FileReader(
                        new ClassPathResource("orders.json").getFile()))) {
            for(String line; (line = br.readLine()) != null; ) {
              order = objectMapper.readValue(line, TransportationOrder.class);
              orders.add(order);
            }

          } catch (IOException e) {
                e.printStackTrace();
        }
         return orders;
       }

       @Test

public void testGetOrder() throws Exception {
     //call GET "/transportationorders/{truck}"  application/json

     when(repository.findById("8962ZKR")).thenReturn(Optional.of(

              new TransportationOrder("28","8962ZKR",1591682400000L,

              40.4562191,-3.8707211,1591692196000L,42.0206372,-4.5330132,

              0,0.0,0.0,0)));

    // Perform the GET request
    mockMvc.perform(MockMvcRequestBuilders
            .get("/transportationorders/8962ZKR")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()) // Expect HTTP 200 OK
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Expect JSON response
            .andExpect(jsonPath("$.toid").value("28")) // Validate the order ID
            .andExpect(jsonPath("$.truck").value("8962ZKR")) // Validate the truck ID
            .andExpect(jsonPath("$.originDate").value(1591682400000L)) // Validate the start time
            .andExpect(jsonPath("$.originLat").value(40.4562191)) // Validate the start latitude
            .andExpect(jsonPath("$.originLong").value(-3.8707211)) // Validate the start longitude
            .andExpect(jsonPath("$.dstDate").value(1591692196000L)) // Validate the end time
            .andExpect(jsonPath("$.dstLat").value(42.0206372)) // Validate the end latitude
            .andExpect(jsonPath("$.dstLong").value(-4.5330132)) // Validate the end longitude
            .andExpect(jsonPath("$.lastDate").value(0)) // Validate the status
            .andExpect(jsonPath("$.lastLat").value(0.0)) // Validate the distance
            .andExpect(jsonPath("$.lastLong").value(0.0)) // Validate the duration
            .andExpect(jsonPath("$.st").value(0.0)); // Validate the fuel consumption

} 
}