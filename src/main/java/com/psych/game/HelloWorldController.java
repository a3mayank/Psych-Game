package com.psych.game;

import com.psych.game.model.Game;
import com.psych.game.model.GameMode;
import com.psych.game.model.Player;
import com.psych.game.model.Question;
import com.psych.game.repositories.GameRepository;
import com.psych.game.repositories.PlayerRepository;
import com.psych.game.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev-test")
public class HelloWorldController {
    @Autowired // dependency inversion in spring
    private PlayerRepository playerRepository;

    @Autowired // dependency inversion in spring
    private QuestionRepository questionRepository;

    @Autowired // dependency inversion in spring
    private GameRepository gameRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/populate")
    public String populateDB() {
        gameRepository.deleteAll();
        playerRepository.deleteAll();
        questionRepository.deleteAll();

        // add palyers to DB
        Player luffy = new Player.Builder()
                .alias("Monkey D. Luffy")
                .email("luffy@psych.com")
                .saltedHashedPassword("mugiwara")
                .build();
        playerRepository.save(luffy);

        Player robin = new Player.Builder()
                .alias("Nico Robin")
                .email("robin@psych.com")
                .saltedHashedPassword("poneglyph")
                .build();
        playerRepository.save(robin);

        // Add game to DB
        Game game = new Game();
        game.setGamemode(GameMode.IS_THIS_A_FACT);
        game.setLeader(luffy);
        game.getPlayers().add(luffy);
        gameRepository.save(game);

        //add question to DB
        questionRepository.save(new Question(
                "What is the most important Poneglyph",
                "Rio Poneglyph",
                GameMode.IS_THIS_A_FACT));

        return "Populated";
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable(name="id") Long id) {
        return questionRepository.findById(id).orElseThrow();
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable(name="id") Long id) {
        return playerRepository.findById(id).orElseThrow();
    }

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Game getGameByID(@PathVariable(name="id") Long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    // create above 2 functions for
    // Games
    // Admins
    // Questions
    // Rounds
    // ContentWriters
}
