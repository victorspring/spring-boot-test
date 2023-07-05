package ru.yandex.practicum.repository;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    private List<UserDto> list = new ArrayList<>();

    public void add(UserDto userDto){
        list.add(userDto);
    }

    public List<UserDto> get(){
        return list;
    }

}
