package th.go.rd.bankaccount.controller;

import org.springframework.web.bind.annotation.*;
import th.go.rd.bankaccount.data.BankAccountRepository;
import th.go.rd.bankaccount.model.BankAccount;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

    private BankAccountRepository repository;

    public BankAccountRestController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer/{id}")
    public List<BankAccount> getAllCustomerId(@PathVariable int id) {
        return repository.findByCustomerId(id);
    }

    @GetMapping
    public List<BankAccount> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getOne(@PathVariable int id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public BankAccount create(@RequestBody BankAccount bankAccount){
        repository.save(bankAccount);
        return repository.findById(bankAccount.getId()).get();
    }
    //หรือจะประกาศเป็น Optional  จะไม่ต้อง .get()
//    public Optional<BankAccount> create(@RequestBody BankAccount bankAccount){
//        repository.save(bankAccount);
//        return repository.findById(bankAccount.getId());
//    }

    @PutMapping("/{id}")
    public BankAccount update(@PathVariable int id,
                              @RequestBody BankAccount bankAccount) {
        BankAccount record = repository.findById(id).get();
        record.setBalance(bankAccount.getBalance());
        repository.save(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public BankAccount delete(@PathVariable int id) {
        BankAccount record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }



}
