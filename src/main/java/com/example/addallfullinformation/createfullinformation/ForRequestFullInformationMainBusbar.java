package com.example.addallfullinformation.createfullinformation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForRequestFullInformationMainBusbar {

    private long id;
    private String nameOfBusbar;
    private List<Long> numbersBusbarsIncludedInMain;

}
