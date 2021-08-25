package com.galvanize;

import org.junit.jupiter.api.Test;

import javax.swing.event.ChangeEvent;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TelevisionTest {


    @Test
    public void tvConstructedOffTest(){
        //setup
        Channel hbo = new Channel(1, "HBO");
        Channel showtime = new Channel(2, "Showtime");
        Channel abc = new Channel(3,"ABC");
        List<Channel> channels = Arrays.asList(hbo, showtime, abc);
        Television TV1 = new Television(channels);

        //execution

        boolean expected = false;
        boolean actual = TV1.getTvPoweredOn();

        //assertion
        assertEquals(actual, false);

    }

    @Test
    public void powerOnTVTest(){
        //setup
        Channel hbo = new Channel(1, "HBO");
        Channel showtime = new Channel(2, "Showtime");
        Channel abc = new Channel(3,"ABC");
        List<Channel> channels = Arrays.asList(hbo, showtime, abc);
        Television TV1 = new Television(channels);

        //execution
        TV1.turnOn();
        TV1.isOn();
        System.out.println("The is " + TV1.getPowerStatus() + " true or false? " + TV1.getTvPoweredOn());

        //assertion
        assertEquals(true, TV1.getTvPoweredOn());

    }



    @Test
    void isPowerOffTest()
    {
        //setup
        Channel hbo = new Channel(1, "HBO");
        Channel showtime = new Channel(2, "Showtime");
        Channel abc = new Channel(3,"ABC");
        List<Channel> channels = Arrays.asList(hbo, showtime, abc);
        Television TV1 = new Television(channels);

        //execution
        TV1.turnOff();
        TV1.isOn();
        System.out.println("The is " + TV1.getPowerStatus() + " true or false? " + TV1.getTvPoweredOn());

        //assertion
        assertEquals(false, TV1.getTvPoweredOn());

    }


    @Test
    public void currentChannelTest(){
        //setup
        Channel hbo = new Channel(1, "HBO");
        Channel showtime = new Channel(2, "Showtime");
        Channel abc = new Channel(3,"ABC");
        List<Channel> channels = Arrays.asList(hbo, showtime, abc);
        Television TV1 = new Television(channels);

        //exectuion
        TV1.currentChannel();

        //assertion
        String expected = "1 - HBO";
        String actual = TV1.currentChannel();
        System.out.println(TV1.currentChannel());
        assertEquals(expected, actual);
    }

    @Test
    public void increaseChannelTest(){
        //setup
        Channel hbo = new Channel(1, "HBO");
        Channel showtime = new Channel(2, "Showtime");
        Channel abc = new Channel(3,"ABC");
        List<Channel> channels = Arrays.asList(hbo, showtime, abc);
        Television TV1 = new Television(channels);

        //exectuion
        TV1.increaseChannel();
        TV1.currentChannel();


        //assertion
        String expected = channels.get(TV1.getChannelNumber()).getChannelNumber() + " - " + channels.get(TV1.getChannelNumber()).getChannelName();

        String actual = TV1.currentChannel();
        assertEquals(expected, actual);
    }

    @Test
    public void decreaseChannelTest(){
        //setup
        Channel hbo = new Channel(1, "HBO");
        Channel showtime = new Channel(2, "Showtime");
        Channel abc = new Channel (3, "ABC");
        List<Channel> channels = Arrays.asList(hbo, showtime, abc);
        Television TV1  = new Television(channels);

        //execution
        TV1.decreaseChannel();


        System.out.println(TV1.currentChannel());
        //assertion
        String expected = channels.get(TV1.getChannelNumber()).getChannelNumber() + " - " + channels.get(TV1.getChannelNumber()).getChannelName();
        String actual = TV1.currentChannel();
        assertEquals(expected, actual);

    }

//last bracket
}