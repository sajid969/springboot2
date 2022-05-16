package com.rusucarla.Services;

import com.rusucarla.Dto.*;
import com.rusucarla.Util.Session;
import com.rusucarla.Util.SessionManager;
import com.rusucarla.entity.*;
import com.rusucarla.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    LendingsRepository lendingsRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookRepository bookRepository;


    @Transactional
    public int register(FullUserDto fullUserDto) {
        User user = new User();
        Login login = new Login();

        Login login2 = loginRepository.findByUsername(fullUserDto.getUsername());
        if (login2 != null)
            return -1;//duplicate username
        user.setName(fullUserDto.getName());
        user.setPhoneNumber(fullUserDto.getPhoneNumber());
        user.setEmail(fullUserDto.getEmail());
        user.setAddress(fullUserDto.getAddress());
        user.setCnp(fullUserDto.getCnp());
        login.setRole(Role.USER);
        login.setUsername(fullUserDto.getUsername());
        login.setUserFK(user);
        login.setPassword(fullUserDto.getPassword());
        user.setLogin(login);

        loginRepository.save(login);
        usersRepository.save(user);
        return 0;
    }

    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public TokenDto login(LoginDto loginDto) {
        Login login = loginRepository.findByUsername(loginDto.getUsername());
        if (login != null) {
            if (login.getPassword().equals(loginDto.getPassword())) {
                //SUCCESS
                Session session = new Session(loginDto.getUsername(), Instant.now(), Session.EXPIRATION_TIME, login.getRole());
                String token = SessionManager.add(session);
                //System.out.println(SessionManager.getSessionMap().keySet());
                //SessionManager.printMap();
                return new TokenDto(token, "TOKEN OK");
            } else {
                return null;
            }
        } else {
            //ERROR
            return null;
        }
    }

    @Transactional
    public void update(UserDto userDto, Session session) {
        User user = usersRepository.findByUsername(session.getUsername());
        if (userDto.getName() != null)
            user.setName(userDto.getName());
        if (userDto.getCnp() != null)
            user.setCnp(userDto.getCnp());
        if (userDto.getAddress() != null)
            user.setAddress(userDto.getAddress());
        if (userDto.getEmail() != null)
            user.setEmail(userDto.getEmail());
        if (userDto.getPhoneNumber() != null)
            user.setPhoneNumber(userDto.getPhoneNumber());
    }

    @Transactional
    public int addCustomer(CustomerDto customerDto) {
        Customer customer =  new Customer();
        Customer customer1 = customerRepository.findCustomerByCnp(customerDto.getCnp());

        if (customer1 != null)
            return -1;//duplicate cnp
        customer.setAddress(customerDto.getAddress());
        customer.setCnp(customerDto.getCnp());
        customer.setDateOpened(new Date(System.currentTimeMillis()));
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());

       customerRepository.save(customer);
        return 0;
    }

    @Transactional
    public int addBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setPublisher(bookDto.getPublisher());
        book.setPrice(bookDto.getPrice());

        bookRepository.save(book);
        return 0;
    }

    @Transactional
    public List<Book> searchBookByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    @Transactional
    public int deleteCustomer(String cnp) {
        Customer customer = customerRepository.findCustomerByCnp(cnp);
        if (customer == null)
            return -1;//duplicate cnp
        customerRepository.delete(customer);
        return 0;
    }

    @Transactional
    public int deleteBook(int id) {
        Book book = bookRepository.findBookById(id);
        if (book == null)
            return -1;//duplicate cnp
        bookRepository.delete(book);
        return 0;
    }

    @Transactional
    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findCustomerByCnp(customerDto.getCnp());
        if (customerDto.getName() != null)
            customer.setName(customerDto.getName());
        if (customerDto.getCnp() != null)
            customer.setCnp(customerDto.getCnp());
        if (customerDto.getAddress() != null)
            customer.setAddress(customerDto.getAddress());
        if (customerDto.getEmail() != null)
            customer.setEmail(customerDto.getEmail());
        if (customerDto.getPhone() != null)
            customer.setPhone(customerDto.getPhone());
    }

    @Transactional
    public void updateBook(BookDto bookDto) {
        Book book = bookRepository.findBookById(bookDto.getId());
        if (bookDto.getTitle() != null)
            book.setTitle(bookDto.getTitle());
        if (bookDto.getPublisher() != null)
            book.setPublisher(bookDto.getPublisher());
        if (bookDto.getPrice() >= 0)
            book.setPrice(bookDto.getPrice());
    }

    @Transactional
    public int addLendings(LendDto lendDto) {
        Optional<Customer> ocustomer = customerRepository.findById(lendDto.getCustomerId());
        Optional<Book> obook = bookRepository.findById(lendDto.getBookId());

        if (!ocustomer.isPresent())
            return -1;// customer not found
        if (!obook.isPresent())
            return -1;// book not found

        Customer customer = ocustomer.get();
        Book book = obook.get();

        Lendings lendings = new Lendings();
        lendings.setPenalty(lendDto.getPenalty());
        lendings.setReturn_date(new Date(System.currentTimeMillis()+604800000));
        lendings.setCustomerFK(customer);
        lendings.setBookFK(book);
        customer.getLendings().add(lendings);
        book.getLendings().add(lendings);
        lendingsRepository.save(lendings);

        return 0;
    }

    @Transactional
    public int deleteLendings(int id) {
        Optional<Lendings> olendings = lendingsRepository.findById(id);
        if (!olendings.isPresent())
            return -1;// lending not found
        Lendings lendings = olendings.get();

        Customer customer = new Customer();
        customer.getLendings().remove(lendings);

        Book book = new Book();
        book.getLendings().remove(lendings);

        lendingsRepository.delete(lendings);
        return 0;
    }

     public ArrayList<CustomerDto> getCustomers() {
        ArrayList<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
        List<Customer> customers;
        customers = customerRepository.findAll();
        for (Customer c : customers) {
            customerDtos.add(new CustomerDto(c.getId(),c.getName(),c.getEmail(),c.getPhone(),c.getCnp(),c.getAddress()));
        }
        return customerDtos;
    }

    public ArrayList<BookDto> getBooks() {
        ArrayList<BookDto> bookDtos = new ArrayList<BookDto>();
        List<Book> books;
        books = bookRepository.findAll();
        for (Book b : books) {
            bookDtos.add(new BookDto(b.getId(),b.getTitle(),b.getPublisher(),b.getPrice()));
        }
        return bookDtos;
    }

     public ArrayList<SpecialLendingsDto> getLendings() {
        ArrayList<SpecialLendingsDto> lendingsDtos = new ArrayList<SpecialLendingsDto>();
        List<Lendings> lendings;
        lendings = lendingsRepository.findAll();
        Customer customer = new Customer();
        Book book = new Book();

        for (Lendings l : lendings) {
            lendingsDtos.add(new SpecialLendingsDto(l.getCustomerFK().getId(), l.getCustomerFK().getName(),l.getBookFK().getId(), l.getBookFK().getTitle(),l.getReturn_date(),l.getPenalty()));
        }
        return lendingsDtos;
    }

    public ArrayList<BookDto> getLendingsByUser(String name) {
        Customer customer = new Customer();
        customer = customerRepository.findCustomerByName(name);
        List<Lendings> lendings = lendingsRepository.findAllByCustomerFK(customer);
        Book book;
        ArrayList<BookDto> bookDtos = new ArrayList<>();

        for (Lendings l : lendings) {
                book = l.getBookFK();
                bookDtos.add(new BookDto(book.getId(),book.getTitle(),book.getPublisher(),book.getPrice()));
        }

        return bookDtos;
    }

    public ArrayList<CustomerDto> getLendingsByBook(int id) {
        Book book = new Book();
        book = bookRepository.findBookById(id);
        List<Lendings> lendings = lendingsRepository.findAllByBookFK(book);
        Customer customer;
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();

        for (Lendings l : lendings) {
            customer=l.getCustomerFK();
            customerDtos.add(new CustomerDto(customer.getId(),customer.getName(),customer.getEmail(),customer.getPhone(),customer.getCnp(),customer.getAddress()));
        }
        return customerDtos;
    }

    public void logout(String token) {
        SessionManager.getSessionMap().remove(token);
    }
}
