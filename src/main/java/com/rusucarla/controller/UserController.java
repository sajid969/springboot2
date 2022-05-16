package com.rusucarla.controller;

import com.rusucarla.Dto.*;
import com.rusucarla.Services.UserService;
import com.rusucarla.Util.RequestValidator;
import com.rusucarla.Util.Session;
import com.rusucarla.Util.SessionManager;

import com.rusucarla.entity.Book;
import com.rusucarla.entity.Role;
import com.rusucarla.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/searchBook")
    public List<Book> searchBookByTitle(String title) {
        return userService.searchBookByTitle(title);
    }

    @GetMapping(value = "/viewProfile")
    public ResponseEntity<UserDto> viewProfile(@RequestHeader("token") String token) {
        System.out.println("yo" + token);
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            System.out.println("eloo");
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            User user = userService.findByUsername(session.getUsername());
            return new ResponseEntity<>(new UserDto(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getCnp()), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updateProfile")
    public ResponseEntity<StringObj> updateProfile(@RequestBody UserDto userDto, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.FORBIDDEN);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            userService.update(userDto, session);
            return new ResponseEntity<>(new StringObj("SUCCESS : PROFILE UPDATED"), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getRole")
    public ResponseEntity<Role> getRole(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(session.getRole(), HttpStatus.OK);
    }

    @PostMapping(value = "/addCustomer")
    public ResponseEntity<StringObj> addCustomer(@RequestBody CustomerDto customerDto, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            System.out.println("nu");
            return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session)) {
                System.out.println("da");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (userService.addCustomer(customerDto) == 0) {
                System.out.println("dada");
                return new ResponseEntity<>(new StringObj("SUCCESS : CUSTOMER ADDED"), HttpStatus.OK);
            } else {
                System.out.println("dadada");
                return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(value = "/addBook")
    public ResponseEntity<StringObj> addBook(@RequestBody BookDto bookDto, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            System.out.println("nu");
            return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session)) {
                System.out.println("da");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (userService.addBook(bookDto) == 0) {
                System.out.println("dada");
                return new ResponseEntity<>(new StringObj("SUCCESS : BOOK ADDED"), HttpStatus.OK);
            } else {
                System.out.println("dadada");
                return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(value = "/deleteCustomer")
    public ResponseEntity<StringObj> deleteCustomer(@RequestBody String cnp, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            System.out.println("nu");
            return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session)) {
                System.out.println("da");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (userService.deleteCustomer(cnp) == 0) {
                System.out.println("dada");
                return new ResponseEntity<>(new StringObj("SUCCESS : CUSTOMER DELETED"), HttpStatus.OK);
            } else {
                System.out.println("dadada");
                return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(value = "/deleteBook")
    public ResponseEntity<StringObj> deleteBook(@RequestBody int id, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            System.out.println("nu");
            return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session)) {
                System.out.println("da");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (userService.deleteBook(id) == 0) {
                System.out.println("dada");
                return new ResponseEntity<>(new StringObj("SUCCESS : BOOK DELETED"), HttpStatus.OK);
            } else {
                System.out.println("dadada");
                return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(value = "/updateCustomer")
    public ResponseEntity<StringObj> updateCustomer(@RequestBody CustomerDto customerDto, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.FORBIDDEN);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            userService.updateCustomer(customerDto);
            return new ResponseEntity<>(new StringObj("SUCCESS : PROFILE UPDATED"), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updateBook")
    public ResponseEntity<StringObj> updateBook(@RequestBody BookDto bookDto, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.FORBIDDEN);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            userService.updateBook(bookDto);
            return new ResponseEntity<>(new StringObj("SUCCESS : PROFILE UPDATED"), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/deleteLendings")
    public ResponseEntity<StringObj> deleteLendings(@RequestBody int id, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            System.out.println("nu");
            return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session)) {
                System.out.println("da");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (userService.deleteLendings(id) == 0) {
                System.out.println("dada");
                return new ResponseEntity<>(new StringObj("SUCCESS : BOOK DELETED"), HttpStatus.OK);
            } else {
                System.out.println("dadada");
                return new ResponseEntity<>(new StringObj("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(value = "/addLendings")
    public ResponseEntity<String> addLendings(@RequestBody LendDto lendDto, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            switch (userService.addLendings(lendDto)) {
                case 0:
                    return new ResponseEntity<>("SUCCESS: LENDING ADDED", HttpStatus.OK);
                case -1:
                    return new ResponseEntity<>("ERROR -1", HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(value = "/getCustomers")
    public ResponseEntity<ArrayList<CustomerDto>> getCustomers(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            ArrayList<CustomerDto> customerDtos = userService.getCustomers();
            return new ResponseEntity<>(customerDtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getBooks")
    public ResponseEntity<ArrayList<BookDto>> getBooks(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            ArrayList<BookDto> bookDtos = userService.getBooks();
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getLendings")
    public ResponseEntity<ArrayList<SpecialLendingsDto>> getLendings(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);

        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            ArrayList<SpecialLendingsDto> specialLendingsDtos = userService.getLendings();
            return new ResponseEntity<>(specialLendingsDtos, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/getLendingsByUser")
    public ResponseEntity<ArrayList<BookDto>> getLendingsByUser(String name, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);

        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            ArrayList<BookDto> bookDtos = userService.getLendingsByUser(name);
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/getLendingsByBook")
    public ResponseEntity<ArrayList<CustomerDto>> getLendingsByBook(int id, @RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);

        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            ArrayList<CustomerDto> customerDtos = userService.getLendingsByBook(id);
            return new ResponseEntity<>(customerDtos, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody FullUserDto fullUserDto) {
        System.out.println(fullUserDto);
        switch (userService.register(fullUserDto)) {
            case 0:
                return new ResponseEntity<>("SUCCESS : USER REGISTERED", HttpStatus.OK);
            case -1:
                return new ResponseEntity<>("DUPLICATE", HttpStatus.CONFLICT);
            default:
                return new ResponseEntity<>("UNKNOWN ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout(@RequestHeader("token") String token) {
        if (!SessionManager.getSessionMap().containsKey(token))
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        else {
            userService.logout(token);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        TokenDto tokenDto = userService.login(loginDto);
        System.out.println(loginDto.getUsername());
        if (tokenDto != null)
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(tokenDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
