import javax.swing.*;
import java.awt.*;

public class TaskManager {
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JComboBox<String> priorityBox, statusBox;
    private JButton addButton, removeButton, completeButton, inProgressButton, notStartedButton, clearButton, sortButton, editButton, statsButton;

    public TaskManager() {
        frame = new JFrame("Менеджер Завдань");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(173, 216, 230));

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(180, 220, 240));

        taskInput = new JTextField();
        priorityBox = new JComboBox<>(new String[]{"Високий", "Середній", "Низький"});
        statusBox = new JComboBox<>(new String[]{"Не розпочато", "В процесі", "Завершено"});
        addButton = UIHelper.createStyledButton("Додати Завдання");

        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(priorityBox, BorderLayout.WEST);
        inputPanel.add(statusBox, BorderLayout.EAST);
        inputPanel.add(addButton, BorderLayout.SOUTH);
        frame.add(inputPanel, BorderLayout.NORTH);

        // Updated layout with GridBagLayout for better control
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(180, 220, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        
        removeButton = UIHelper.createStyledButton("Видалити Завдання");
        completeButton = UIHelper.createStyledButton("Завершено");
        inProgressButton = UIHelper.createStyledButton("В процесі");
        notStartedButton = UIHelper.createStyledButton("Не розпочато");
        clearButton = UIHelper.createStyledButton("Очистити Все");
        sortButton = UIHelper.createStyledButton("Сортувати");
        editButton = UIHelper.createStyledButton("Редагувати");
        statsButton = UIHelper.createStyledButton("Статистика");

        // Add buttons to buttonPanel with GridBagConstraints
        buttonPanel.add(removeButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(completeButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(inProgressButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(notStartedButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(clearButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(sortButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(editButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(statsButton, gbc);

        // Add exit button
        JButton exitButton = UIHelper.createStyledButton("Вийти");
        exitButton.setBackground(new Color(255, 100, 100));
        exitButton.setForeground(Color.BLACK);
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridx = 2;
        gbc.gridy = 3;
        buttonPanel.add(exitButton, gbc);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        TaskHandler handler = new TaskHandler(taskListModel, taskList, taskInput, priorityBox, statusBox);

        addButton.addActionListener(e -> handler.addTask());
        removeButton.addActionListener(e -> handler.removeTask());
        completeButton.addActionListener(e -> handler.updateTaskStatus("Завершено"));
        inProgressButton.addActionListener(e -> handler.updateTaskStatus("В процесі"));
        notStartedButton.addActionListener(e -> handler.updateTaskStatus("Не розпочато"));
        clearButton.addActionListener(e -> handler.clearTasks());
        sortButton.addActionListener(e -> handler.sortTasks());
        editButton.addActionListener(e -> handler.editTask());
        statsButton.addActionListener(e -> handler.showStatistics());

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManager::new);
    }
}
