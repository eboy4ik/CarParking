package ru.golyashchuk.carparking.tester;

import ru.golyashchuk.carparking.utils.RectCoordinateSystem;

public class test {
    public static void main(String[] args) {
//        RectCoordinateSystem rcs = new RectCoordinateSystem(500, 100, 0);
        double angle = Math.toRadians(45);
        RectCoordinateSystem rcs = new RectCoordinateSystem(500, 100, angle);

        System.out.println(rcs.getAnotherRCSPoint(100, 200));

    }
}
