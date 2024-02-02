package cl.demotest.client.controller;

import cl.demotest.client.dto.ResponseWrapper;
import cl.demotest.client.dto.UserRequestDTO;
import cl.demotest.client.service.UserService;
import cl.demotest.client.util.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * UserController Class
 *
 * @author chidalgogalvez@gmail.com
 */

@Slf4j
@RestController
@RequestMapping(path = "user")
@CrossOrigin(origins = {"*"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseWrapper> addUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("POST: /user/add");
        log.info("Data:{}", userRequestDTO);

        ResponseWrapper response = Utils.formatResponse(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                userService.addUser(userRequestDTO));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseWrapper> findUserById(
            @PathVariable(value = "uuid") UUID uuid) {

        log.info("GET: /user/uuid={}", uuid);

        ResponseWrapper response = Utils.formatResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                userService.findUserById(uuid));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseWrapper> updateUserById(
            @PathVariable(value = "uuid") UUID uuid,
            @Valid @RequestBody UserRequestDTO updatedUserRequestDTO)  {

        log.info("PUT: /user/uuid={}", uuid);
        log.info("Data:{}", updatedUserRequestDTO);

        ResponseWrapper response = Utils.formatResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                userService.updateUserById(updatedUserRequestDTO, uuid));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<ResponseWrapper> deleteUserById(
            @PathVariable(value = "uuid") UUID uuid) {

        log.info("DELETE: /user/uuid={}", uuid);

        ResponseWrapper response = Utils.formatResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                userService.deleteUserById(uuid));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/prueba", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseWrapper> prueba() {

        log.info("GET: /user/prueba");

        ResponseWrapper response = Utils.formatResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                userService.prueba());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
