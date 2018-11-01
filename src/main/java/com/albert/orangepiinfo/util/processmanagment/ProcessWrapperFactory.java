package com.albert.orangepiinfo.util.processmanagment;

import org.springframework.stereotype.Component;

@Component
public class ProcessWrapperFactory {

    public ProcessWrapper getWrapper(String command){

        return new ProcessWrapper(command);
    }
}
