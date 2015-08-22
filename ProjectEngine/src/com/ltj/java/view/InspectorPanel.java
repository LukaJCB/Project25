package com.ltj.java.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.shared.engine.Animation;
import com.ltj.shared.engine.Collider;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.primitives.BoxCollider;

@SuppressWarnings("serial")
public class InspectorPanel extends JPanel {

	private MainInspector mainPanel;

	private JButton addAnimationButton;
	private int currentId;
	private JTabbedPane tabbedPane;
	private JPanel animationPanel,collisionPanel;
	private JButton addColliderButton;
	private DefaultListModel<Collider> colliders;

	private DefaultListModel<Entry<String, Animation>> animations;


	public InspectorPanel(final GLCanvas canvas, final JoglRenderer renderer){
		setPreferredSize(new Dimension(200,500));
		tabbedPane = new JTabbedPane();

		mainPanel = new MainInspector(canvas);

		tabbedPane.addTab("Main", mainPanel);
		tabbedPane.setVisible(false);
		add(tabbedPane);


		setupAnimationTab();

		setupCollisionTab();
	}

	private void setupAnimationTab() {
		animationPanel = new JPanel();
		animationPanel.setLayout(new BoxLayout(animationPanel, BoxLayout.Y_AXIS));

		addAnimationButton = new JButton("Add Animation");
		animationPanel.add(addAnimationButton);
		
		animations = new DefaultListModel<Entry<String, Animation>>();
		final JList<Entry<String,Animation>> animationList = new JList<Entry<String,Animation>>(animations);
		
		JScrollPane scrollPane = new JScrollPane(animationList);
		scrollPane.setPreferredSize(new Dimension(100,200));
		animationPanel.add(scrollPane);
		addAnimationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO add animation
				RenderObject o = Engine.getAllObjects().get(currentId);
				o.addAnimation("Hello", 1, 1, true, 2);
				for (Entry<String,Animation> entry : o.getAllAnimations()){

					animations.addElement(entry);
				}
				animationList.setSelectedIndex(animations.size()-1);
			}
		});
	}

	private void setupCollisionTab() {
		collisionPanel = new JPanel();
		collisionPanel.setLayout(new BoxLayout(collisionPanel, BoxLayout.Y_AXIS));

		addColliderButton = new JButton("Add Collider");

		collisionPanel.add(addColliderButton);

		colliders = new DefaultListModel<Collider>();
		final JList<Collider> colliderList = new JList<Collider>(colliders);
		JScrollPane scrollPane = new JScrollPane(colliderList);
		scrollPane.setPreferredSize(new Dimension(180, 200));
		addColliderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RenderObject o = Engine.getAllObjects().get(currentId);
				BoxCollider collider = new BoxCollider();
				o.addCollider(collider);
				colliders.addElement(collider);
				colliderList.setSelectedIndex(colliders.size()-1);
			}
		});
		
		collisionPanel.add(scrollPane);
		
		final ColliderInspector colliderInspector = new ColliderInspector();
		colliderInspector.setPreferredSize(new Dimension(tabbedPane.getPreferredSize().width
				,tabbedPane.getPreferredSize().height/2));
		collisionPanel.add(colliderInspector);
		
		colliderList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (colliderList.getSelectedValue() != null){
					colliderInspector.openInspector(colliderList.getSelectedValue());
				}
			}
		});
		tabbedPane.addTab("Collider", collisionPanel);
	}

	public void openInspector(int selectedIndex) {
		tabbedPane.setVisible(true);
		tabbedPane.setSelectedIndex(0);
		
		mainPanel.openInspector(selectedIndex);
		
		RenderObject o = Engine.getAllObjects().get(selectedIndex);
		colliders.clear();
		if (o.getColliders()!= null){
			for (Collider c : o.getColliders()){
				colliders.addElement(c);
			}

		}
		boolean full = (o.getClass() != EmptyObject.class);
		if (full){

			if (o.getNumCols() > 1 || o.getNumRows() > 1){
				tabbedPane.addTab("Animation", animationPanel);
			} else {
				tabbedPane.remove(animationPanel);

			}

		}
	}

	
	
}
