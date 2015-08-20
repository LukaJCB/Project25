package com.ltj.java.view;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.Line;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.utils.MatrixHelper;

public class CanvasMouseListener implements MouseInputListener, MouseWheelListener {

	private float lastX;
	private float lastY;
	private GLCanvas canvas;
	private float scrollSpeed;
	private JList<RenderObject> list;
	private float[] lastCoordsWorld;
	private boolean scalingMode;
	private ListSelectionListener selectionListener;

	public CanvasMouseListener(GLCanvas canvas,JList<RenderObject> list, ListSelectionListener selectionListener) {
		this(canvas,list,selectionListener,0.01f);
	}

	public CanvasMouseListener(GLCanvas canvas, JList<RenderObject> list,ListSelectionListener selectionListener,float scrollSpeed) {
		this.canvas = canvas;
		this.list = list;
		this.scrollSpeed = scrollSpeed;
		this.selectionListener = selectionListener;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!SwingUtilities.isRightMouseButton(e)){
			
			Line mouseClick = getLineFromMouse(e);
			
			RenderObject selectedObject = null;
			for (RenderObject o : Engine.getAllObjects().values()){
				if (mouseClick.intersectsWith(o)){
					selectedObject = o;
				}
			}

			if (selectedObject != null){
				list.setSelectedIndex(selectedObject.getId());
				lastCoordsWorld = mouseClick.getIntersection();

				if (pointNearEdgeOf(lastCoordsWorld,selectedObject)){
					enableScalingMode();


				}

			}
		}

		lastX = e.getX();
		lastY = e.getY();

	}

	private void enableScalingMode() {
		scalingMode = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		scalingMode = false;
		canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (list.getSelectedValue() == null){
			return;
		}
		Line mouseHover = getLineFromMouse(e);

		if (mouseHover.intersectsWith(list.getSelectedValue())){
			if (pointNearEdgeOf(mouseHover.getIntersection(), list.getSelectedValue())){
				canvas.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
			} else {
				canvas.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		} else {
			canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)){
			Camera.setLookAt(Camera.getLookAt()[0] - scrollSpeed*(e.getX() - lastX),
					Camera.getLookAt()[1] + scrollSpeed*(e.getY() - lastY));
		} else {
			Line mouseDrag = getLineFromMouse(e);
			mouseDrag.intersectXYPlane();

			RenderObject o = list.getSelectedValue();

			if (scalingMode){
				o.scale(1+ 2*(mouseDrag.getIntersection()[0] - lastCoordsWorld[0]), 1);
			} else {
				o.translate(mouseDrag.getIntersection()[0] - lastCoordsWorld[0]
						,mouseDrag.getIntersection()[1] - lastCoordsWorld[1]);
			}

			selectionListener.valueChanged(null);
			lastCoordsWorld = mouseDrag.getIntersection();
		}


		canvas.display();
		lastX = e.getX();
		lastY = e.getY();
	}

	private Line getLineFromMouse(MouseEvent e) {
		float normalX = MatrixHelper.normalizeCoordiantes(e.getX(),canvas.getWidth());
		float normalY = -MatrixHelper.normalizeCoordiantes(e.getY(), canvas.getHeight());
		Line mouseClick = Camera.normalized2DCoordsToLine(normalX,normalY);
		return mouseClick;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (Camera.getEyePos()[2] > 1 || e.getWheelRotation() > 0){
			Camera.setDistance(Camera.getEyePos()[2] + e.getWheelRotation());
			canvas.display();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	public void registerListener() {
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addMouseWheelListener(this);
	}

	private boolean pointNearEdgeOf(float[] point, RenderObject object) {
		if (point[0] > object.getX() + object.getWidth()/2 - 0.1f){
			return true;
		}
		return false;
	}


}
