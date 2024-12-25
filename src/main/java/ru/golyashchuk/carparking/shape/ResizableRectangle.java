package ru.golyashchuk.carparking.shape;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ResizableRectangle extends Group {
    public final int DEFAULT_STROKE_WIDTH = 5;
    private Rectangle rectangle;
    private double minWidth = 0;
    private double minHeight = 0;
    private Line rightSide = new Line();
    private Line leftSide = new Line();
    private Line upperSide = new Line();
    private Line bottomSide = new Line();
    private Circle upperLeftCorner = new Circle(DEFAULT_STROKE_WIDTH);
    private Circle upperRightCorner = new Circle(DEFAULT_STROKE_WIDTH);
    private Circle bottomLeftCorner = new Circle(DEFAULT_STROKE_WIDTH);
    private Circle bottomRightCorner = new Circle(DEFAULT_STROKE_WIDTH);
    private UpdateListener updateListener;

    public UpdateListener getUpdateListener() {
        return updateListener;
    }

    public void setUpdateListener(UpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    public ResizableRectangle(double width, double height) {
        rectangle = new Rectangle(width, height);
        initialize();
    }

    public ResizableRectangle(double x, double y, double width, double height) {
        rectangle = new Rectangle(x, y, width, height);
        initialize();
    }

    public double getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(double minWidth) {
        this.minWidth = Math.max(minWidth, 0);
    }

    public double getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(double minHeight) {
        this.minHeight = Math.max(minHeight, 0);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Line getRightSide() {
        return rightSide;
    }

    public Line getLeftSide() {
        return leftSide;
    }

    public Line getUpperSide() {
        return upperSide;
    }

    public Line getBottomSide() {
        return bottomSide;
    }

    public Circle getUpperLeftCorner() {
        return upperLeftCorner;
    }

    public Circle getUpperRightCorner() {
        return upperRightCorner;
    }

    public Circle getBottomLeftCorner() {
        return bottomLeftCorner;
    }

    public Circle getBottomRightCorner() {
        return bottomRightCorner;
    }


    public void setX(double x) {
        rectangle.setX(x);
        updateSidesAndCorners();
        if (updateListener != null) {
            updateListener.onMove(rectangle.getX(), rectangle.getY());
        }
    }

    public void setY(double y) {
        rectangle.setY(y);
        updateSidesAndCorners();
        if (updateListener != null) {
            updateListener.onMove(rectangle.getX(), rectangle.getY());
        }
    }

    public void setSidesStrokeWidth(double w) {
        rightSide.setStrokeWidth(w);
        leftSide.setStrokeWidth(w);
        upperSide.setStrokeWidth(w);
        bottomSide.setStrokeWidth(w);
    }

    private void initialize() {
        setSidesStrokeWidth(DEFAULT_STROKE_WIDTH);
        addMouseEventsToSides();
        addMouseEventsToCorners();
        updateSidesAndCorners();
        setAllSidesAndCornersTransparent();
        super.getChildren().addAll(rectangle, leftSide, rightSide,
                upperSide, bottomSide, upperLeftCorner, upperRightCorner,
                bottomLeftCorner, bottomRightCorner);
    }

    private void setAllSidesAndCornersTransparent() {
        rightSide.setStroke(Color.TRANSPARENT);
        leftSide.setStroke(Color.TRANSPARENT);
        bottomSide.setStroke(Color.TRANSPARENT);
        upperSide.setStroke(Color.TRANSPARENT);
        bottomLeftCorner.setFill(Color.TRANSPARENT);
        upperLeftCorner.setFill(Color.TRANSPARENT);
        bottomRightCorner.setFill(Color.TRANSPARENT);
        upperRightCorner.setFill(Color.TRANSPARENT);
    }


    private void addMouseEventsToSides() {
        rightSide.setOnMouseEntered(this::onMouseEnteredVerticalLine);
        rightSide.setOnMouseExited(this::onMouseExited);
        rightSide.setOnMouseDragged(this::onMouseDraggedRightSide);

        leftSide.setOnMouseEntered(this::onMouseEnteredVerticalLine);
        leftSide.setOnMouseExited(this::onMouseExited);
        leftSide.setOnMouseDragged(this::onMouseDraggedLeftSide);


        upperSide.setOnMouseEntered(this::onMouseEnteredHorizontalLine);
        upperSide.setOnMouseExited(this::onMouseExited);
        upperSide.setOnMouseDragged(this::onMouseDraggedUpperSide);

        bottomSide.setOnMouseEntered(this::onMouseEnteredHorizontalLine);
        bottomSide.setOnMouseExited(this::onMouseExited);
        bottomSide.setOnMouseDragged(this::onMouseDraggedBottomSide);
    }

    private void addMouseEventsToCorners() {
        bottomRightCorner.setOnMouseEntered(this::onMouseEnteredSELine);
        bottomRightCorner.setOnMouseExited(this::onMouseExited);
        bottomRightCorner.setOnMouseDragged(this::onMouseDraggedBottomRightCorner);

        upperLeftCorner.setOnMouseEntered(this::onMouseEnteredSELine);
        upperLeftCorner.setOnMouseExited(this::onMouseExited);
        upperLeftCorner.setOnMouseDragged(this::onMouseDraggedUpperLeftCorner);

        bottomLeftCorner.setOnMouseEntered(this::onMouseEnteredNELine);
        bottomLeftCorner.setOnMouseExited(this::onMouseExited);
        bottomLeftCorner.setOnMouseDragged(this::onMouseDraggedBottomLeftCorner);

        upperRightCorner.setOnMouseEntered(this::onMouseEnteredNELine);
        upperRightCorner.setOnMouseExited(this::onMouseExited);
        upperRightCorner.setOnMouseDragged(this::onMouseDraggedUpperRightCorner);
    }


    private void onMouseEnteredSELine(MouseEvent mouseEvent) {
        ((Shape) (mouseEvent.getSource())).getScene().setCursor(Cursor.SE_RESIZE);
    }

    private void onMouseEnteredNELine(MouseEvent mouseEvent) {
        ((Shape) (mouseEvent.getSource())).getScene().setCursor(Cursor.NE_RESIZE);
    }

    private void onMouseDraggedBottomSide(MouseEvent mouseEvent) {
        this.rectangle.setHeight(Math.max(minHeight, mouseEvent.getY() - rectangle.getY()));
        updateSidesAndCorners();
    }

    private void onMouseDraggedUpperRightCorner(MouseEvent mouseEvent) {
        double bottomY = rectangle.getY() + rectangle.getHeight();
        rectangle.setY(Math.min(mouseEvent.getY(), bottomY - minHeight));
        rectangle.setWidth(Math.max(minWidth, mouseEvent.getX() - rectangle.getX()));
        rectangle.setHeight(Math.max(minHeight, bottomY - mouseEvent.getY()));
        updateSidesAndCorners();
    }

    private void onMouseDraggedBottomLeftCorner(MouseEvent mouseEvent) {
        double rightX = rectangle.getX() + rectangle.getWidth();
        rectangle.setX(Math.min(rightX - minWidth, mouseEvent.getX()));
        rectangle.setWidth(Math.max(minWidth, rightX - mouseEvent.getX()));
        rectangle.setHeight(Math.max(minHeight, mouseEvent.getY() - rectangle.getY()));
        updateSidesAndCorners();
    }

    private void onMouseDraggedUpperLeftCorner(MouseEvent mouseEvent) {
        double rightX = rectangle.getX() + rectangle.getWidth();
        double bottomY = rectangle.getY() + rectangle.getHeight();
        rectangle.setX(Math.min(rightX - minWidth, mouseEvent.getX()));
        rectangle.setY(Math.min(mouseEvent.getY(), bottomY - minHeight));
        rectangle.setWidth(Math.max(minWidth, rightX - mouseEvent.getX()));
        rectangle.setHeight(Math.max(minHeight, bottomY - mouseEvent.getY()));
        updateSidesAndCorners();
    }

    private void onMouseDraggedBottomRightCorner(MouseEvent mouseEvent) {
        rectangle.setWidth(Math.max(minWidth, mouseEvent.getX() - rectangle.getX()));
        rectangle.setHeight(Math.max(minHeight, mouseEvent.getY() - rectangle.getY()));
        updateSidesAndCorners();
    }

    private void onMouseDraggedUpperSide(MouseEvent mouseEvent) {
        double bottomY = rectangle.getY() + rectangle.getHeight();
        rectangle.setY(Math.min(mouseEvent.getY(), bottomY - minHeight));
        rectangle.setHeight(Math.max(minHeight, bottomY - mouseEvent.getY()));
        updateSidesAndCorners();
    }

    private void onMouseDraggedLeftSide(MouseEvent mouseEvent) {
        double rightX = rectangle.getX() + rectangle.getWidth();
        rectangle.setX(Math.min(mouseEvent.getX(), rightX - minWidth));
        rectangle.setWidth(Math.max(minWidth, rightX - mouseEvent.getX()));
        updateSidesAndCorners();
    }

    private void onMouseDraggedRightSide(MouseEvent mouseEvent) {
        this.rectangle.setWidth(Math.max(minWidth, mouseEvent.getX() - rectangle.getX()));
        updateSidesAndCorners();
    }

    private void onMouseExited(MouseEvent mouseEvent) {
        ((Shape) (mouseEvent.getSource())).getScene().setCursor(Cursor.DEFAULT);
    }

    private void onMouseEnteredHorizontalLine(MouseEvent mouseEvent) {
        ((Line) (mouseEvent.getSource())).getScene().setCursor(Cursor.V_RESIZE);
    }

    private void onMouseEnteredVerticalLine(MouseEvent mouseEvent) {
        ((Line) (mouseEvent.getSource())).getScene().setCursor(Cursor.H_RESIZE);
    }

    private void updateSidesAndCorners() {
        updateSides();
        updateCorners();
        if (updateListener != null) {
            updateListener.onResize(rectangle.getWidth(), rectangle.getHeight());
        }
    }

    private void updateSides() {
        updateLeftSide();
        updateRightSide();
        updateUpperSide();
        updateBottomSide();
    }

    private void updateBottomSide() {
        bottomSide.setStartX(rectangle.getX());
        bottomSide.setStartY(rectangle.getY() + rectangle.getHeight());
        bottomSide.setEndX(rectangle.getX() + rectangle.getWidth());
        bottomSide.setEndY(rectangle.getY() + rectangle.getHeight());
    }

    private void updateUpperSide() {
        upperSide.setStartX(rectangle.getX());
        upperSide.setStartY(rectangle.getY());
        upperSide.setEndX(rectangle.getX() + rectangle.getWidth());
        upperSide.setEndY(rectangle.getY());
    }

    private void updateRightSide() {
        rightSide.setStartX(rectangle.getX() + rectangle.getWidth());
        rightSide.setStartY(rectangle.getY());
        rightSide.setEndX(rectangle.getX() + rectangle.getWidth());
        rightSide.setEndY(rectangle.getY() + rectangle.getHeight());
    }

    private void updateLeftSide() {
        leftSide.setStartX(rectangle.getX());
        leftSide.setStartY(rectangle.getY());
        leftSide.setEndX(rectangle.getX());
        leftSide.setEndY(rectangle.getY() + rectangle.getHeight());
    }

    private void updateCorners() {
        updateUpperLeftCorner();
        updateUpperRightCorner();
        updateBottomLeftCorner();
        updateBottomRightCorner();
    }

    private void updateBottomRightCorner() {
        bottomRightCorner.setCenterX(rectangle.getX() + rectangle.getWidth());
        bottomRightCorner.setCenterY(rectangle.getY() + rectangle.getHeight());
    }

    private void updateBottomLeftCorner() {
        bottomLeftCorner.setCenterX(rectangle.getX());
        bottomLeftCorner.setCenterY(rectangle.getY() + rectangle.getHeight());
    }

    private void updateUpperRightCorner() {
        upperRightCorner.setCenterX(rectangle.getX() + rectangle.getWidth());
        upperRightCorner.setCenterY(rectangle.getY());
    }

    private void updateUpperLeftCorner() {
        upperLeftCorner.setCenterX(rectangle.getX());
        upperLeftCorner.setCenterY(rectangle.getY());
    }

    public void setSidesColor(Color color) {
        leftSide.setStroke(color);
        rightSide.setStroke(color);
        bottomSide.setStroke(color);
        upperSide.setStroke(color);
    }
}
