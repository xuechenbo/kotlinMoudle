package com.monebac.com.thread;

import android.os.Environment;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * created by xxx
 * on 2021/8/12
 */
public class StaticNumTest {

    private List mList;

    @Before
    public void setUp() throws Exception {
        mList = new ArrayList<String>();
        mList.add("1");
        mList.add("2");
        mList.add("3");
        mList.add("4");
    }


    @Test
    public void changeOne() {
        change(mList);
        assertEquals(mList.get(0), "0");
    }


    public void change(List mList) {
        mList.set(0, "0");
        mList.set(1, "1");
        mList.set(2, "2");
        mList.set(3, "3");
    }


    /**
     * 58934  61638  5000 10000
     */
}