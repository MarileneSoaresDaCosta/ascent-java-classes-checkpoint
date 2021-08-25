package com.galvanize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Television {




    private String powerStatus;
    private Boolean tvPoweredOn;
    private List<Channel> channels;
    private int channelNumber;

    //Getters and Setters
    public String getPowerStatus() {
        return powerStatus;
    }

    public Boolean getTvPoweredOn() {
        return tvPoweredOn;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public Television (List<Channel> channels) {
    powerStatus = "off";
    tvPoweredOn = false;
    this.channels = channels;
    this.channelNumber = 0;
    }



    public boolean  isOn() {
        if (this.powerStatus == "on")
        {
            this.tvPoweredOn = true;

        } else if (this.powerStatus == "off"){
            this.tvPoweredOn = false;
        }
        return this.tvPoweredOn;
     }



    public void turnOn() {
        this.powerStatus = "on";
    }



    public void turnOff() {
        this.powerStatus = "off";
    }

    public String currentChannel(){
         return this.channels.get(this.channelNumber).getChannelNumber() + " - " + channels.get(this.channelNumber).getChannelName();
    }

    public void increaseChannel() {
        int channelUp = getChannelNumber();
        channelUp += 1;
        if (channelUp > channels.size() -1)
        {
            channelUp = 0;
        }
        setChannelNumber(channelUp);
    }

    public void decreaseChannel() {
        int channelDown = getChannelNumber();
        channelDown -= 1;
        if (channelDown < 0 )
        {
            channelDown = channels.size() - 1;
        }
        setChannelNumber(channelDown);
    }


//last bracket
}




