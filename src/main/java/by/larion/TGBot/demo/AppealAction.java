package by.larion.TGBot.demo;

import by.larion.TGBot.demo.entity.District;
import by.larion.TGBot.demo.impl.DistrictButtonModeService;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AppealAction implements Action {

    private final String action;
    private final DistrictButtonModeService districtButtonModeService;

    public AppealAction(String action, DistrictButtonModeService districtButtonModeService) {
        this.action = action;
        this.districtButtonModeService = districtButtonModeService;
    }

    @Override
    public BotApiMethod handle(Update update) {

        var massage = update.getMessage();
        var chatId = massage.getChatId();
        var text = "Выберите свой район:";

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        District districtButton = districtButtonModeService.getDistrict(chatId);
        buttons.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text(getDistrictButton(districtButton, districtButton))
                                .callbackData("Район " + districtButton)
                                .build()
                )
        );
//        for (District district : districtButton) {
//        }
        return SendMessage.builder()
                .text(text)
                .chatId(massage.getChatId().toString())
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons)
                        .build()).build();

    }


    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var email = msg.getText();
        // userRepository.save(new User(email));
        var text = "Пользователь успешно зарегистрирован:" + email;
        return new SendMessage(chatId, text);
    }

    private String getDistrictButton(District saved, District district) {
        return saved != district ? district + " ✅" : district.name();
    }

//    private districtButtons(){
//        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        for (District district : District.values()) {
//            buttons.add(
//                    Arrays.asList(
//                            InlineKeyboardButton.builder()
//                                    .text(district.name())
//                                    .callbackData("Район " + district)
//                                    .build()
//                    )
//            );
//        }
//        return buttons;
//    }
}
