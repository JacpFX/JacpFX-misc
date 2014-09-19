/*
 * **********************************************************************
 *
 *  Copyright (C) 2010 - 2014
 *
 *  [Component.java]
 *  JACPFX Project (https://github.com/JacpFX/JacpFX/)
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language
 *  governing permissions and limitations under the License.
 *
 *
 * *********************************************************************
 */

package com.trivadis.com.component;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class JavaFX3D extends Application {

    private PerspectiveCamera camera;
    private final double cameraQuantity = 100.0;
    private final double sceneWidth = 600;
    private final double sceneHeight = 600;
    private double mouseXold = 0;
    private double mouseYold = 0;
    private final double cameraYLimit = 15;
    private final double rotateModifier = 25;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group sceneRoot = new Group();
        Scene scene = new Scene(sceneRoot, sceneWidth, sceneHeight);
        scene.setFill(Color.GREEN);
        camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-1000);
        scene.setCamera(camera);


        Rotate xRotate = new Rotate(0,0,0,0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0,0,0,0, Rotate.Y_AXIS);
        Rotate zRotate = new Rotate(0,0,0,0, Rotate.Z_AXIS);

        camera.getTransforms().addAll(xRotate, yRotate, zRotate);
        scene.addEventHandler(MouseEvent.ANY, event-> {
            double mouseXnew = event.getSceneX();
            double mouseYnew = event.getSceneY();
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                double pitchRotate = xRotate.getAngle() + (mouseYnew - mouseYold) / rotateModifier;
                pitchRotate = pitchRotate > cameraYLimit ? cameraYLimit : pitchRotate;
                pitchRotate = pitchRotate < -cameraYLimit ? -cameraYLimit : pitchRotate;
                xRotate.setAngle(pitchRotate);
                double yawRotate=yRotate.getAngle()-(mouseXnew-mouseXold) / rotateModifier;
                yRotate.setAngle(yawRotate);
//				zRotate.setAngle(zRotate.getAngle() + 1);
            }
            mouseXold = mouseXnew;
            mouseYold = mouseYnew;
        });

        primaryStage.setTitle("SimleScene3D");
        primaryStage.setScene(scene);
        primaryStage.show();

        final Cylinder cylinder = new Cylinder(50, 100);
        cylinder.setRotationAxis(Rotate.X_AXIS);
        cylinder.setRotate(45);
        cylinder.setTranslateZ(-200);

        final PhongMaterial greenMaterial=new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);
        final Box cube = new Box(50, 50, 50);
        cube.setMaterial(greenMaterial);

        final PhongMaterial redMaterial=new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
        final Sphere sphere = new Sphere(50);
        sphere.setMaterial(redMaterial);

        cube.setRotationAxis(Rotate.Y_AXIS);
        cube.setRotate(45);
        cube.setTranslateX(-150);
        cube.setTranslateY(-150);
        cube.setTranslateZ(-150);

        sphere.setTranslateX(150);
        sphere.setTranslateY(150);
        sphere.setTranslateZ(-150);

        scene.addEventHandler(MouseEvent.ANY, event-> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED && event.isAltDown()) {
                PickResult pickResult = event.getPickResult();
                if (pickResult != null) {
                    Node node = pickResult.getIntersectedNode();
                    if (node != null) {
                        node.setVisible(false);
                    }
                }
            }
        });


        final PhongMaterial blueMetal = new PhongMaterial();
        blueMetal.setDiffuseColor(Color.DARKBLUE);
        blueMetal.setSpecularColor(Color.BLUE);
        cylinder.setMaterial(blueMetal);

        scene.setOnKeyPressed(event -> {
            double change = cameraQuantity;
            KeyCode keycode = event.getCode();
            if (keycode == KeyCode.W) {camera.setTranslateZ(camera.getTranslateZ() + change); }
            if (keycode == KeyCode.S) {camera.setTranslateZ(camera.getTranslateZ() - change); }

            if (keycode == KeyCode.A) {camera.setTranslateX(camera.getTranslateX() - change); }
            if (keycode == KeyCode.D) {camera.setTranslateX(camera.getTranslateX() + change); }

            if (keycode == KeyCode.SPACE) {
                sphere.setVisible(true);
                cube.setVisible(true);
                cylinder.setVisible(true);
            }
        });

        sceneRoot.getChildren().addAll(cylinder, cube, sphere);
    }
}
