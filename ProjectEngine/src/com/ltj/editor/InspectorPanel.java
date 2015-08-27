package com.ltj.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.shared.engine.Animation;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.BehaviourManipulator;
import com.ltj.shared.engine.Collider;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.primitives.BoxCollider;

@SuppressWarnings("serial")
public class InspectorPanel extends JTabbedPane {

	private MainInspector mainPanel;

	private JButton addAnimationButton;
	private int currentId;
	private JPanel animationPanel,collisionPanel;
	private JPanel behaviourPanel;

	private JButton addColliderButton;
	private JButton addBehaviourButton;

	private DefaultListModel<Collider> colliders;

	private DefaultListModel<Entry<String, Animation>> animations;
	
	private ArrayList<Field> behaviourFields;

	public InspectorPanel(final GLCanvas canvas, final JoglRenderer renderer){
		setPreferredSize(new Dimension(230,500));

		mainPanel = new MainInspector(canvas);

		addTab("Main", mainPanel);
		setVisible(false);


		setupAnimationTab();

		setupCollisionTab();
		
		setupBehaviourTab();
	}

	private void setupAnimationTab() {
		animationPanel = new JPanel();
		animationPanel.setLayout(new BoxLayout(animationPanel, BoxLayout.Y_AXIS));

		addAnimationButton = new JButton("Add Animation");
		animationPanel.add(addAnimationButton);
		
		animations = new DefaultListModel<Entry<String, Animation>>();
		final JList<Entry<String,Animation>> animationList = new JList<Entry<String,Animation>>(animations);
		
		JScrollPane scrollPane = new JScrollPane(animationList);
		scrollPane.setPreferredSize(new Dimension(180,200));
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
		
		final AnimationInspector animInspector = new AnimationInspector();
		animInspector.setPreferredSize(new Dimension(getPreferredSize().width
				,getPreferredSize().height/2));
		animationPanel.add(animInspector);
		
		animationList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (animationList.getSelectedValue() != null){
					animInspector.openInspector(animationList.getSelectedValue().getValue());
				}
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
		colliderInspector.setPreferredSize(new Dimension(getPreferredSize().width
				,getPreferredSize().height/2));
		collisionPanel.add(colliderInspector);
		
		colliderList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (colliderList.getSelectedValue() != null){
					colliderInspector.openInspector(colliderList.getSelectedValue());
				}
			}
		});
		addTab("Collider", collisionPanel);
	}
	
	private void setupBehaviourTab(){
		JPanel mainBehaviourTab = new JPanel();
		mainBehaviourTab.setLayout(new BoxLayout(mainBehaviourTab, BoxLayout.Y_AXIS));
		behaviourPanel = new JPanel();
		behaviourPanel.setLayout(new BoxLayout(behaviourPanel, BoxLayout.Y_AXIS));

		addBehaviourButton = new JButton("Add Behaviour");
		mainBehaviourTab.add(addBehaviourButton);
		mainBehaviourTab.add(behaviourPanel);
		addTab("Behaviour", mainBehaviourTab);
		
		behaviourFields = new ArrayList<Field>();
	}

	public void openInspector(int selectedIndex) {
		setVisible(true);
		setSelectedIndex(0);
		
		currentId = selectedIndex;
		
		mainPanel.openInspector(selectedIndex);
		
		final RenderObject o = Engine.getAllObjects().get(selectedIndex);
		colliders.clear();
		if (o.getColliders()!= null){
			for (Collider c : o.getColliders()){
				colliders.addElement(c);
			}
		}
		animations.clear();
		if (o.getAllAnimations() != null){
			for (Entry<String, Animation> e : o.getAllAnimations()){
				animations.addElement(e);
			}
		}
		
		openBehaviourTab(o);
		
		boolean full = (o.getClass() != EmptyObject.class);
		if (full){

			if (o.getNumCols() > 1 || o.getNumRows() > 1){
				addTab("Animation", animationPanel);
			} else {
				remove(animationPanel);

			}

		}
	}

	private void openBehaviourTab(final RenderObject o) {
		behaviourFields.clear();
		behaviourPanel.removeAll();
		if (o.getBehaviour() != null){
			for (final Field f : BehaviourManipulator.getFields(o.getBehaviour())){
				
				behaviourFields.add(f);
				
				Box box = new Box(BoxLayout.X_AXIS);
				box.setMaximumSize(new Dimension(200, 30));
				box.add(new JLabel(f.getName()));
				if (f.getType() == Boolean.TYPE){
					final JCheckBox checkBox = new JCheckBox();
					checkBox.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							BehaviourManipulator.manipulateField(o.getBehaviour(), f.getName(), checkBox.isSelected());
							
						}
					});
					box.add(checkBox);
					
				} else {
					final JTextField text = new JTextField();
					text.addKeyListener(new BehaviourKeyListener(f, o.getBehaviour(), text));
					box.add(text);
				} 
				behaviourPanel.add(box);
				behaviourPanel.revalidate();
			}
		}
	}
	
	class BehaviourKeyListener implements KeyListener {

		private Class<?> type;
		private Behaviour<?> behaviour;
		private String name;
		private JTextField textField;

		public BehaviourKeyListener(Field field, Behaviour<?> behaviour,JTextField textField){
			type = field.getType();
			name = field.getName();
			this.behaviour = behaviour;
			this.textField = textField;
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (type == Integer.TYPE){
				BehaviourManipulator.manipulateField(behaviour, name, Integer.parseInt(textField.getText()));
			} else if (type == Float.TYPE){
				BehaviourManipulator.manipulateField(behaviour, name, Float.parseFloat(textField.getText()));
			}
		}
		
	}

	
	
}
