package com.galvanize;

import com.galvanize.util.ClassProxy;
import com.galvanize.util.InstanceProxy;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

    private static final String CHANNEL_CLASSNAME = "com.galvanize.Channel";
    private static final String TELEVISION_CLASSNAME = "com.galvanize.Television";
    private static final String IS_ON = "isOn";
    private static final String TURN_ON = "turnOn";
    private static final String TURN_OFF = "turnOff";
    private static final String CURRENT_CHANNEL = "currentChannel";
    private static final String INCREASE_CHANNEL = "increaseChannel";
    private static final String DECREASE_CHANNEL = "decreaseChannel";
    private static final String[] CHANNEL_NAMES = {"HBO", "NBC", "CBS", "ABC", "TNT"};

    private ClassProxy ChannelProxy;
    private ClassProxy TelevisionProxy;
    private List<Object> channels;

    static <K> TypeToken<List<K>> channelToken(TypeToken<K> channelToken) {
        return new TypeToken<List<K>>() {
        }.where(new TypeParameter<K>() {
        }, channelToken);
    }

    @BeforeAll
    public static void validateSolutionStructure() {
        ClassProxy ChannelProxy = ClassProxy.classNamed(CHANNEL_CLASSNAME)
                .ensureConstructor(Integer.class, String.class);

        ClassProxy.classNamed(TELEVISION_CLASSNAME)
                .ensureConstructor(channelToken(TypeToken.of(ChannelProxy.getDelegate())));
    }

    @BeforeEach
    public void setup() {
        ChannelProxy = ClassProxy.classNamed(CHANNEL_CLASSNAME)
                .ensureConstructor(Integer.class, String.class);

        TelevisionProxy = ClassProxy.classNamed(TELEVISION_CLASSNAME)
                .ensureConstructor(channelToken(TypeToken.of(ChannelProxy.getDelegate())));

        channels = IntStream.range(0, CHANNEL_NAMES.length)
                .mapToObj(i -> ChannelProxy.newInstance(i + 1, CHANNEL_NAMES[i]).getDelegate())
                .collect(toList());
    }

    @Test
    public void powerTogglesCorrectlyForTurnOnAndTurnOff() throws Throwable {
        TelevisionProxy
                .ensureMethod(m -> m
                        .isPublic()
                        .named(TURN_ON)
                        .returns(Void.TYPE))
                .ensureMethod(m -> m
                        .isPublic()
                        .named(TURN_OFF)
                        .returns(Void.TYPE))
                .ensureMethod(m -> m
                        .isPublic()
                        .named(IS_ON)
                        .returns(Boolean.TYPE));

        InstanceProxy television = TelevisionProxy.newInstance(channels);
        assertEquals(
                false,
                television.invoke(IS_ON),
                "Your isOn method should return false when a new television is initialized");

        television.invoke(TURN_ON);
        assertEquals(
                true,
                television.invoke(IS_ON),
                "Your isOn method should return true when a television has had turnOn called");

        television.invoke(TURN_OFF);
        assertEquals(
                false,
                television.invoke(IS_ON),
                "Your isOn method should return false when a television has had turnOff called");
    }

    @Test
    public void channelsIncrementAndDecrementCorrectly() throws Throwable {
        TelevisionProxy
                .ensureMethod(m -> m
                        .isPublic()
                        .named(INCREASE_CHANNEL)
                        .returns(Void.TYPE))
                .ensureMethod(m -> m
                        .isPublic()
                        .named(DECREASE_CHANNEL)
                        .returns(Void.TYPE))
                .ensureMethod(m -> m
                        .isPublic()
                        .named(CURRENT_CHANNEL)
                        .returns(String.class));

        InstanceProxy television = TelevisionProxy.newInstance(channels);
        assertEquals(
                channelLabel(1),
                television.invoke(CURRENT_CHANNEL),
                "Your currentChannel method should return a string representation of the channel in {NUMBER} - {NAME} format");

        television.invoke(INCREASE_CHANNEL);
        television.invoke(INCREASE_CHANNEL);
        assertEquals(
                channelLabel(3),
                television.invoke(CURRENT_CHANNEL),
                "Your currentChannel method should return the third channel after being increased twice");

        television.invoke(DECREASE_CHANNEL);
        assertEquals(
                channelLabel(2),
                television.invoke(CURRENT_CHANNEL),
                "Your currentChannel method should return the second channel after being decreased from the third");

        television.invoke(DECREASE_CHANNEL);
        television.invoke(DECREASE_CHANNEL);
        assertEquals(
                channelLabel(CHANNEL_NAMES.length),
                television.invoke(CURRENT_CHANNEL),
                "When decreased below first channel, current channel should be the last channel");

        television.invoke(INCREASE_CHANNEL);
        assertEquals(
                channelLabel(1),
                television.invoke(CURRENT_CHANNEL),
                "When increased beyond last channel, current channel should be the first");
    }

    private String channelLabel(int i) {
        return String.format("%s - %s", i, CHANNEL_NAMES[i - 1]);
    }

}
