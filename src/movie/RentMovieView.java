package movie;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.Item;
import common.OkCancelView;

public class RentMovieView extends OkCancelView {
	private static final long serialVersionUID = 1L;
	private ActionListener actionListener;
	private final JLabel nameLabel;
	private final JLabel phoneNumberLabel;
	private final JLabel addressLabel;
	private final JLabel customerIDLabel;
	private final JLabel accountLabel;
	private final JLabel membershipLabel;
	private final JLabel sourceLabel;
	private final JList<Item> sourceList;
	private final JScrollPane sourceScrollPane;
	private final JLabel destLabel;
	private final JList<Item> destTable;
	private final JScrollPane destScrollPane;
	private final JButton addButton;
	private final JButton removeButton;
	private RentMovieViewListener viewListener;

	private static final String ADD_BUTTON_LABEL = "Add >>";
	private static final String REMOVE_BUTTON_LABEL = "<< Remove";
	private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available";
	private static final String DEFAULT_DEST_CHOICE_LABEL = "Chosen";
	private static final String CUSTOMER_INFO_TITLE = "Customer Info";

	public RentMovieView() {

		String RENT_MOVIE_TITLE = "Rent Movie";
		JLabel actionTitle = getActionTitle();
		setActionTitleText(RENT_MOVIE_TITLE);
		layout.putConstraint(SpringLayout.WEST, actionTitle, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, actionTitle, 5, SpringLayout.NORTH, this);

		JLabel customerInfoTitle = new JLabel(CUSTOMER_INFO_TITLE);
		actionTitle.setFont(new Font(this.getFont().getFamily(), Font.PLAIN, 16));
		layout.putConstraint(SpringLayout.WEST, customerInfoTitle, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, customerInfoTitle, 20, SpringLayout.SOUTH, actionTitle);

		this.nameLabel = new JLabel();
		add(this.nameLabel);

		this.addressLabel = new JLabel();
		add(this.addressLabel);

		this.phoneNumberLabel = new JLabel();
		add(this.phoneNumberLabel);

		this.customerIDLabel = new JLabel();
		add(this.customerIDLabel);

		this.accountLabel = new JLabel();
		add(this.accountLabel);

		this.membershipLabel = new JLabel();
		add(this.membershipLabel);

		sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
		add(sourceLabel);

		sourceList = new JList<Item>();
		sourceList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && sourceList.getSelectedIndex() != -1) {
					viewListener.listSelectionChanged(sourceList.getSelectedValuesList(), destTable.getSelectedValuesList());
				}
			}
		});

		sourceScrollPane = new JScrollPane(sourceList);
		add(sourceScrollPane);

		destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
		destLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(sourceLabel);

		destTable = new JList<Item>();
		add(destLabel);

		destScrollPane = new JScrollPane(destTable);
		add(destScrollPane);

		addButton = new JButton(ADD_BUTTON_LABEL);
		add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				viewListener.addItems(sourceList.getSelectedValuesList());
			}
		});

		removeButton = new JButton(REMOVE_BUTTON_LABEL);
		add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				viewListener.removeItems(destTable.getSelectedValuesList());
			}
		});

		setOkButtonLabel("Rent");
		addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewListener.okPressed();
			}
		});

		setCancelButtonLabel("Back");
		addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewListener.cancelPressed();
			}
		});
	}

	public void setCustomer(String name, String address, String phoneNumber, String customerID, String accountID, String membershipID) {

	}

	public void addViewListener(RentMovieViewListener viewListener) {
		this.viewListener = viewListener;
	}

	private void performLayout() {
		int width = getWidth();
		int height = getHeight();
		int verticalMargin = 20;
		int horizontalMargin = 7;
		int labelHeight = 14;
		int middleWidth = 100;
		int sideWidth = (width - middleWidth) / 2 - horizontalMargin;

		int offset = verticalMargin;
		sourceLabel.setLocation(horizontalMargin, offset);
		sourceLabel.setSize(sideWidth, labelHeight);

		int middleInset = horizontalMargin + sideWidth;

		int rightInset = middleInset + middleWidth;
		destLabel.setLocation(rightInset, offset);
		destLabel.setSize(sideWidth, labelHeight);
		offset += labelHeight + horizontalMargin;

		int listHeight = height - offset - horizontalMargin;
		sourceScrollPane.setLocation(horizontalMargin, offset);
		sourceScrollPane.setSize(sideWidth, listHeight);
		sourceScrollPane.validate();

		destScrollPane.setLocation(rightInset, offset);
		destScrollPane.setSize(sideWidth, listHeight);
		destScrollPane.validate();

		int buttonHeight = 22;
		offset += (listHeight - (2 * buttonHeight + verticalMargin)) / 2;
		int buttonWidth = middleWidth - 2 * horizontalMargin;
		int buttonInset = middleInset + horizontalMargin;

		addButton.setLocation(buttonInset, offset);
		addButton.setSize(buttonWidth, buttonHeight);
		offset += buttonHeight + verticalMargin;

		removeButton.setLocation(buttonInset, offset);
		removeButton.setSize(buttonWidth, buttonHeight);
	}
}
