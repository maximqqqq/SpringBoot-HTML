package by.ShoppingList.SpringBootHTML.controller;

import by.ShoppingList.SpringBootHTML.persist.ShoppingItem;
import by.ShoppingList.SpringBootHTML.persist.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//При запуске приложения подсистема Spring MVC канирует приложение на наличее таких вот классов @Controller
// если она их находит она пытается их настроить для того что бы использовать в качестве оброботчиков HTTP апросов
@Controller
public class ShoppingListController {

    private ShoppingItemRepository repository;

    //Если у конструктора есть пораметры и указана анатация @Autowired то при создании данного класса Spring будет искать
//есть ли в приложении класс который реолизует данный интерфейс ShoppingItemRepository который указан в качестве
//пораметра. И если такой класс есть то он его передат при создании класса в качестве пораметра конструктора и наш
// ShoppingListController будет иметь доступ к Репозиторию. Если токого класса нет то приложение не запустится и
//выскочит ошибка
    @Autowired
    public ShoppingListController(ShoppingItemRepository repository) {
        this.repository = repository;
    }

    //@GetMapping значит что данный метод будет обробатывать HTTP запросы методом get
    //Запрос тип get(Мы вводи данные в строке браузера)
    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item",new ShoppingItem());
        return "index";
    }

    @PostMapping
    public String newShoppingItem(ShoppingItem shoppingItem){
        repository.save(shoppingItem);
        //заставит браузер обновить содержимое страницы после того как была добавлена новая запись в таблицу
        //т.е заставит баузер перейти на(в данном случае на корневую страницу) lpcalhost8080/shopping-list
        return "redirect:/";
    }
//spring.mvc.hiddenmethod.filter.enabled=true в пропертях нужен для удаления
    //В урле мы хотим иметь параметр ("/{id}")
    @DeleteMapping("/{id}")//Что бы spring понял что мы хотим параметр из урла("/{id}") перенести в метод
    public String deleteShoppingItem(@PathVariable("id") Long id){//нужно во входном пар указать@PathVariable
        repository.deleteById(id);
        return "redirect:/";
    }
}
