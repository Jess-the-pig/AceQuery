package henrotaym.env.feature;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import henrotaym.env.ApplicationTest;
import henrotaym.env.entities.Player;
import henrotaym.env.repositories.PlayerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

public class PlayerEndpointFeatureTest extends ApplicationTest {
    @Autowired private MockMvc mockMvc;

    @Autowired private PlayerRepository playerRepository;

    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        playerRepository.deleteAll();

        // Préparation des données via PlayerFactory-like code
        playerRepository.save(new Player(null, "Rafael Nadal", "ESP", LocalDate.of(1986, 6, 3), 2));
        playerRepository.save(
                new Player(null, "Roger Federer", "SUI", LocalDate.of(1981, 8, 8), 3));
        playerRepository.save(
                new Player(null, "Carlos Alcaraz", "ESP", LocalDate.of(2003, 5, 5), 1));
        playerRepository.save(new Player(null, "Andy Murray", "GBR", LocalDate.of(1987, 5, 15), 4));
    }

    @Test
    void testFilterByNameAndNationality() throws Exception {
        var result =
                mockMvc.perform(get("/players").param("name", "rafa").param("nationality", "ESP"))
                        .andExpect(status().isOk())
                        .andReturn();

        List<Player> players = readListFromJson(result.getResponse().getContentAsString());

        assertThat(players).hasSize(1);
        assertThat(players.get(0).getName()).isEqualTo("Rafael Nadal");
    }

    @Test
    void testSortByRankingDescending() throws Exception {
        var result =
                mockMvc.perform(
                                get("/players")
                                        .param("sortBy", "ranking")
                                        .param("sortOrder", "desc"))
                        .andExpect(status().isOk())
                        .andReturn();

        List<Player> players = readListFromJson(result.getResponse().getContentAsString());

        assertThat(players).hasSize(4);
        assertThat(players.get(0).getRanking()).isEqualTo(4); // Andy Murray
        assertThat(players.get(3).getRanking()).isEqualTo(1); // Alcaraz
    }

    @Test
    void testSortByAgeAscending() throws Exception {
        var result =
                mockMvc.perform(get("/players").param("sortBy", "age").param("sortOrder", "asc"))
                        .andExpect(status().isOk())
                        .andReturn();

        List<Player> players = readListFromJson(result.getResponse().getContentAsString());

        assertThat(players).hasSize(4);
        assertThat(players.get(0).getName()).isEqualTo("Carlos Alcaraz"); // plus jeune
        assertThat(players.get(3).getName()).isEqualTo("Roger Federer"); // plus âgé
    }

    @Test
    void testWithoutAnyFilterReturnsAll() throws Exception {
        var result = mockMvc.perform(get("/players")).andExpect(status().isOk()).andReturn();

        List<Player> players = readListFromJson(result.getResponse().getContentAsString());

        assertThat(players).hasSize(4);
    }

    private List<Player> readListFromJson(String json) throws Exception {
        return objectMapper.readerForListOf(Player.class).readValue(json);
    }
}
