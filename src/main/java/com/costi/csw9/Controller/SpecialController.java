package com.costi.csw9.Controller;

import com.costi.csw9.Model.Ajax.MediaInfo;
import com.costi.csw9.Model.Ajax.ProjectInfo;
import com.costi.csw9.Model.Axcel.GameProgress;
import com.costi.csw9.Model.Post;
import com.costi.csw9.Model.User;
import com.costi.csw9.Model.UserRole;
import com.costi.csw9.Service.PostService;
import com.costi.csw9.Service.UserService;
import com.costi.csw9.Util.InfoInitializer;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class SpecialController {
    private static final int requiredFinds = 7;
    private List<ProjectInfo> projects = InfoInitializer.initializeProjects();
    private List<MediaInfo> mediaProjects = InfoInitializer.initializeMedia();

    /*
        Services
     */
    private PostService postService;
    private UserService userService;

    public SpecialController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    private User getCurrentUser(Principal principal) {
        if (principal == null) {
            return new User("NULL", "NULL", "Not Signed In", "error", UserRole.USER);
        }
        String username = principal.getName();
        User u = userService.findByEmail(username);
        return u;
    }

    /*
        JSON responses
     */
    @GetMapping("/api/v1/newsroom/post/{id}")
    public ResponseEntity<Post> getNewsroomPost(@PathVariable Long id, Principal principal) {
        try{
            Post post = postService.loadById(id);
            if(post.isEnabled()){
                return ResponseEntity.ok(post);
            }else{
                if(getCurrentUser(principal).isOwner() || getCurrentUser(principal).isAdmin()){
                    return ResponseEntity.ok(post);
                }else{
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-media")
    public ResponseEntity<List<MediaInfo>> getMedia() {
        return ResponseEntity.ok(mediaProjects);
    }

    @GetMapping("/get-media-analytics")
    @ResponseBody
    public Map<String, Object> getMediaAnalytics() {
        // Construct the JSON response
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("totalProjects", mediaProjects.size());

        int numVideos = 0, numImages = 0, numFlyers = 0, numLogos = 0, numLiveAction = 0, numAnimation = 0, numEras = 0, numYoutube = 0;

        String tempEra = "";
        for(MediaInfo project : mediaProjects){
            String type = project.getType();
            String era = project.getEra();

            // Assorted types
            if(type.equals("live-action")){
                numVideos++;
                numLiveAction++;
            }else if(type.equals("animation")){
                numAnimation++;
                numVideos++;
            }else if(type.equals("logo-card")){
                numLogos++;
                numImages++;
            }else if(type.equals("flyer")){
                numFlyers++;
                numImages++;
            }

            //Number of eras
            if(!tempEra.equals(era)){
                numEras++;
            }

            //YouTube Count
            if(!project.getLink().equals("")){
                numYoutube++;
            }
        }

        jsonResponse.put("videoCount", numVideos);
        jsonResponse.put("imageCount", numImages);
        jsonResponse.put("flyerCount", numFlyers);
        jsonResponse.put("logoCount", numLogos);
        jsonResponse.put("liveActionCount", numLiveAction);
        jsonResponse.put("animationCount", numAnimation);
        jsonResponse.put("eraCount", numEras);
        jsonResponse.put("youtubeCount", numYoutube);

        return jsonResponse;
    }

    @GetMapping("/get-projects")
    public ResponseEntity<List<ProjectInfo>> getProjects() {
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/get-project-analytics")
    @ResponseBody
    public Map<String, Object> getProjectAnalytics() {
        // Construct the JSON response
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("totalProjects", projects.size());

        int numActive = 0, numDiscontinued = 0, numJava = 0, numPython = 0, numOther = 0, numWeb = 0, numRepos = 0;
        for(ProjectInfo project : projects){
            if(project.isDiscontinued()){
                numDiscontinued++;
            }else{
                numActive++;
            }

            if(project.getType().equals("java")){
                numJava++;
            }else if(project.getType().equals("web")){
                numWeb++;
            }else if(project.getType().equals("python")){
                numPython++;
            }else{
                numOther++;
            }

            numRepos += project.getRepositoryLinks().length;
        }

        jsonResponse.put("active", numActive);
        jsonResponse.put("discontinued", numDiscontinued);
        jsonResponse.put("java", numJava);
        jsonResponse.put("python", numPython);
        jsonResponse.put("other", numOther);
        jsonResponse.put("web", numWeb);
        jsonResponse.put("repos", numRepos);

        return jsonResponse;
    }

    /*
        Axcel Game
     */
    @PostMapping("/games/Axcel/start-game")
    @ResponseBody
    public Map<String, Object> startGame(HttpSession session) {
        GameProgress gameProgress = (GameProgress) session.getAttribute("gameProgress");
        String responseBody = "Game started";

        // Start a new name if one was not already created
        if (gameProgress == null) {
            gameProgress = new GameProgress();
            session.setAttribute("gameProgress", gameProgress);
        }else{
            responseBody += "Game started already";
        }

        // Construct the JSON response
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("success", true);
        jsonResponse.put("message", responseBody);

        return jsonResponse;
    }

    @PostMapping("/games/Axcel/found-sprite")
    @ResponseBody
    public Map<String, Object> handleFoundSprite(@RequestParam String spriteName, HttpSession session) {
        GameProgress gameProgress = (GameProgress) session.getAttribute("gameProgress");
        if (gameProgress == null) {
            gameProgress = new GameProgress();
            session.setAttribute("gameProgress", gameProgress);
        }

        Map<String, Object> jsonResponse = new HashMap<>();
        List<String> foundSprites = (gameProgress.getSpriteNamesFound());

        // Add the found sprite name to the game progress
        if (!gameProgress.getSpriteNamesFound().stream().anyMatch(s -> s.equals(spriteName))) {
            gameProgress.getSpriteNamesFound().add(spriteName);

            if (gameProgress.getSpriteNamesFound().size() == requiredFinds) {
                // All sprites found
                gameProgress.setTimeEnd(LocalDateTime.now());
            }

            jsonResponse.put("success", true);
            jsonResponse.put("message", "Sprite found!");
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Sprite already found!");
        }
        jsonResponse.put("foundSprites", foundSprites);

        return jsonResponse;
    }

    @GetMapping("/games/Axcel/check-game-status")
    @ResponseBody
    public Map<String, Object> isGameStarted(HttpSession session) {
        GameProgress gameProgress = (GameProgress) session.getAttribute("gameProgress");
        boolean gameStarted = (gameProgress != null);
        LocalDateTime timeStart = (gameStarted ? gameProgress.getTimeStart() : LocalDateTime.MIN);
        LocalDateTime timeEnd = (gameStarted ? gameProgress.getTimeEnd() : LocalDateTime.MAX);

        List<String> foundSprites = (gameStarted ? gameProgress.getSpriteNamesFound() : null);

        // Construct the JSON response
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("gameStarted", gameStarted);
        jsonResponse.put("timeStart", timeStart);
        jsonResponse.put("timeEnd", timeEnd);
        jsonResponse.put("timeNow", LocalDateTime.now());
        jsonResponse.put("foundSprites", foundSprites);
        jsonResponse.put("quota", requiredFinds);

        return jsonResponse;
    }

    @PostMapping("/games/Axcel/end-game")
    @ResponseBody
    public Map<String, Object> endGame(HttpSession session, HttpServletResponse response) {
        GameProgress gameProgress = (GameProgress) session.getAttribute("gameProgress");
        if (gameProgress != null) {
            // Update the game progress in the session
            session.setAttribute("gameProgress", gameProgress);

            // Invalidate the session to delete the session cookie
            session.invalidate();

            // Construct the JSON response
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Game ended successfully");
            return jsonResponse;
        } else {
            // Game progress not found, handle the error case
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Game progress not found");
            return jsonResponse;
        }
    }
}
