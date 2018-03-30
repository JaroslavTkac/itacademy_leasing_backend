package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.Car;
import lt.swedbank.itacademy.carleasing.beans.documents.CarBrand;
import lt.swedbank.itacademy.carleasing.beans.documents.CarModel;
import lt.swedbank.itacademy.carleasing.beans.responses.CarBrandResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.CarModelResponse;
import lt.swedbank.itacademy.carleasing.repositories.CarBrandRepository;
import lt.swedbank.itacademy.carleasing.repositories.CarModelRepository;
import lt.swedbank.itacademy.carleasing.services.CarsService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CarBrandRepository brandRepository;

    @Mock
    private CarModelRepository modelRepository;

    @Mock
    private CarsService service;

    @InjectMocks
    private CarsController controller;

    private JacksonTester<List<Car>> jsonCarList;

    private CarBrand brand;
    private ObjectId brandId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        brandId = new ObjectId();
        brand = new CarBrand(brandId, "Audi");
    }

    @Test
    public void canRetrieveBrands() throws Exception {
        List<CarBrandResponse> brands = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            brand.setBrand(brand.getBrand() + String.valueOf(i));
            brands.add(new CarBrandResponse(brand));
        }

        //given
        given(controller.getAllBrands()).willReturn(brands);

        //when
        mockMvc.perform(
                get("/cars/brands")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))

                .andExpect(jsonPath("$[0].brand", is("Audi0")))
                .andExpect(jsonPath("$[1].brand", is("Audi01")))
                .andExpect(jsonPath("$[2].brand", is("Audi012")))
                .andExpect(jsonPath("$[3].brand", is("Audi0123")))
                .andExpect(jsonPath("$[4].brand", is("Audi01234")))
                .andReturn().getResponse();

    }

    @Test
    public void canRetrieveModelsByBrand() throws Exception{
        CarModel model;
        List<CarModelResponse> models = new ArrayList<>();

        model = new CarModel(new ObjectId(), "80", brandId);
        models.add(new CarModelResponse(model));
        model = new CarModel(new ObjectId(), "100", brandId);
        models.add(new CarModelResponse(model));
        model = new CarModel(new ObjectId(), "200", brandId);
        models.add(new CarModelResponse(model));

        //given
        given(controller.getAllModelsByBrand(brand.getBrand())).willReturn(models);

        //when
        mockMvc.perform(
                get("/cars/" + brand.getBrand())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))

                .andExpect(jsonPath("$[0].model", is("80")))
                .andExpect(jsonPath("$[1].model", is("100")))
                .andExpect(jsonPath("$[2].model", is("200")))
                .andReturn().getResponse();

    }

    @Test
    public void canRetrieveCarBrandsWithModelsTogether() throws Exception{
        List<Car> allCars = new ArrayList<>();
        CarModel model;
        List<CarModelResponse> models = new ArrayList<>();

        model = new CarModel(new ObjectId(), "80", brandId);
        models.add(new CarModelResponse(model));
        model = new CarModel(new ObjectId(), "100", brandId);
        models.add(new CarModelResponse(model));
        model = new CarModel(new ObjectId(), "200", brandId);
        models.add(new CarModelResponse(model));

        allCars.add(new Car(new CarBrandResponse(brand), models));

        brandId = new ObjectId();
        brand = new CarBrand(brandId, "BMW");

        model = new CarModel(new ObjectId(), "325", brandId);
        models.add(new CarModelResponse(model));
        model = new CarModel(new ObjectId(), "525", brandId);
        models.add(new CarModelResponse(model));
        model = new CarModel(new ObjectId(), "730", brandId);
        models.add(new CarModelResponse(model));

        allCars.add(new Car(new CarBrandResponse(brand), models));


        given(controller.getAllCars()).willReturn(allCars);

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn().getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCarList.write(allCars).getJson()
        );
    }
}