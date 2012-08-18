/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili

import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.shape.Polygon
import javafx.scene.transform.Rotate
import javafx.util.Duration

class Turtle {

    private Location location = new Location(Napili.HOMEX, Napili.HOMEY)

    // Current direction of turtle, in radians
    private double heading = Math.toRadians(Napili.HOMEHEADING)

    private boolean penup = false;
    private Color penColor = Color.BLACK
    private double speed = Napili.TURTLESPEED
    private Polygon turtleShape = new Polygon(-Napili.TURTLEWIDTH / 2, 0, 0, -Napili.TURTLEHEIGHT, Napili.TURTLEWIDTH / 2, 0);
    private Napili napili;

    Turtle(Napili napili) {
        this.napili = napili;
        turtleShape.setFill(Color.BLACK)
        home()
        napili.addNode(turtleShape)
    }

    def propertyMissing(String name) {

        if (name == 'turtle') {
            return this;
        }

        if (this.metaClass.respondsTo(this, name)) {
            return this."$name"()
        }

        throw new MissingPropertyException("No property on Turtle named '$name'")

    }

    def left(double degrees) {
        double radians = Math.toRadians(degrees)
        heading -= radians;
        turn(-degrees)
        return this
    }

    def right(double degrees) {
        double radians = Math.toRadians(degrees)
        heading += radians;
        turn(degrees)
        return this
    }

    def forward(double distance) {
        def newLocation = location.newLocation(heading, distance)
        draw(location, newLocation)
        location = newLocation
        return this
    }

    def home() {
        def degrees = Math.toDegrees(heading)
        def degreeHomeCorrection = Napili.HOMEHEADING - degrees

        heading = Math.toRadians(Napili.HOMEHEADING)

        location = new Location(Napili.HOMEX, Napili.HOMEY)

        Timeline timeline = new Timeline()
        Rotate rot = new Rotate(0, 0, 0);
        turtleShape.transforms.add(rot)

        KeyFrame rotkf = new KeyFrame(Duration.millis(1), new KeyValue(rot.angleProperty(), degreeHomeCorrection));

        KeyFrame turtleX = new KeyFrame(Duration.millis(1), new KeyValue(turtleShape.translateXProperty(), location.x));
        KeyFrame turtleY = new KeyFrame(Duration.millis(1), new KeyValue(turtleShape.translateYProperty(), location.y));

        timeline.getKeyFrames().addAll(turtleX, turtleY, rotkf);

        napili.addAnimation(timeline)
        return this
    }

    def hide() {
        Timeline timeline = new Timeline()
        KeyFrame kf = new KeyFrame(Duration.millis(1), new KeyValue(turtleShape.fillProperty(), Color.TRANSPARENT));
        timeline.getKeyFrames().add(kf)
        napili.addAnimation(timeline)
    }

    def show() {
        Timeline timeline = new Timeline()
        KeyFrame kf = new KeyFrame(Duration.millis(1), new KeyValue(turtleShape.fillProperty(), Color.BLACK));
        timeline.getKeyFrames().add(kf)
        napili.addAnimation(timeline)
    }

    def back(double distance) {
        forward(-distance)
        return this
    }

    def pendown() {
        penup = false
        return this
    }

    def penup() {
        penup = true
        return this
    }

    def pencolor(Color color) {
        penColor = color
        return this
    }

    def speed(double speed) {
        this.speed = 1 / Math.abs(speed)
        return this
    }

    private def draw(Location loc1, Location loc2) {
        draw loc1.x, loc1.y, loc2.x, loc2.y
    }

    private def draw(double x0, double y0, double x1, double y1) {
        double distance = Math.sqrt(Math.pow((x1 - x0),2) + Math.pow((y1 - y0),2) );
        Line line = new Line(x0, y0, x0, y0);
        Timeline timeline = new Timeline();
        line.setStroke(Color.TRANSPARENT);
        Color color;
        if (penup) {
            color = Color.TRANSPARENT;
        } else {
            color = penColor;
        }
        double millis = speed * Math.abs(distance);
        if (millis < 1) millis = 1
        Duration duration = Duration.millis(millis);
        KeyFrame lineX = new KeyFrame(duration, new KeyValue(line.endXProperty(), x1));
        KeyFrame lineY = new KeyFrame(duration, new KeyValue(line.endYProperty(), y1));
        KeyFrame lineColor = new KeyFrame(Duration.ONE, new KeyValue(line.strokeProperty(), color));
        KeyFrame turtleX = new KeyFrame(duration, new KeyValue(turtleShape.translateXProperty(), x1));
        KeyFrame turtleY = new KeyFrame(duration, new KeyValue(turtleShape.translateYProperty(), y1));
        timeline.getKeyFrames().addAll(lineX, lineY, lineColor, turtleX, turtleY);

        napili.addNode(line);
        napili.addAnimation(timeline);
    }

    private def turn(double degrees) {
        double millis = speed * Napili.TURTLETURNSPEED * Math.abs(degrees);
        if (millis < 1) millis = 1;
        Duration duration = Duration.millis(millis);
        Timeline timeline = new Timeline();
        Rotate rot = new Rotate(0, 0, 0);
        turtleShape.getTransforms().add(rot);
        KeyFrame rotkf = new KeyFrame(duration, new KeyValue(rot.angleProperty(), degrees));
        timeline.keyFrames.add(rotkf);

        napili.addAnimation(timeline);
    }


    private class Location {

        def double x;
        def double y;

        Location(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // Given a heading and a distance, compute the resulting coordinates
        Location newLocation(double heading, double distance) {
            double sine = Math.sin(heading);
            double cosine = Math.cos(heading);
            double deltaX = cosine * distance;
            double deltaY = sine * distance;
            return new Location(x + deltaX, y + deltaY);
        }
    }
}

