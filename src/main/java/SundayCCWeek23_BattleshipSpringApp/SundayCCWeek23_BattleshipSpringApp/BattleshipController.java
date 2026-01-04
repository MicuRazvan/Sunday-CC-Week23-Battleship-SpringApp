package SundayCCWeek23_BattleshipSpringApp.SundayCCWeek23_BattleshipSpringApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class BattleshipController {
    private final BattleshipService service;
    private BattleshipGameModel game;

    public BattleshipController(BattleshipService service) { this.service = service; }

    @GetMapping("/") public String index() { return "index"; }

    @PostMapping("/api/start")
    @ResponseBody public BattleshipGameModel start() {
        this.game = new BattleshipGameModel();
        this.game.gameStarted = true;
        service.setupComputerShips(game);
        return game;
    }

    @PostMapping("/api/place")
    @ResponseBody public BattleshipGameModel place(@RequestBody Map<String, String> data) {
        int r = Integer.parseInt(data.get("r")), c = Integer.parseInt(data.get("c")), size = Integer.parseInt(data.get("size"));
        boolean h = data.get("dir").equals("H");
        if (service.canPlace(game.playerBoard, r, c, size, h)) {
            service.place(game.playerBoard, r, c, size, h);
            game.shipsToPlace.removeIf(s -> s.startsWith(data.get("name")));
            if (game.shipsToPlace.isEmpty()) {
                game.placementComplete = true;
                game.status = "All ships placed! Attack the Enemy!";
            }
        }
        return game;
    }

    @PostMapping("/api/attack")
    @ResponseBody public BattleshipGameModel attack(@RequestBody Map<String, Integer> data) {
        int r = data.get("r"), c = data.get("c");
        if (game.computerBoard[r][c] == 'S') game.computerBoard[r][c] = 'H';
        else if (game.computerBoard[r][c] == '~') game.computerBoard[r][c] = 'M';

        if (service.checkWin(game.computerBoard)) game.status = "YOU WIN!";
        else {
            service.computerTurn(game);
            if (service.checkWin(game.playerBoard)) game.status = "COMPUTER WINS!";
        }
        return game;
    }
}