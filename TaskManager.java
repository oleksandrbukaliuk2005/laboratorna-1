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

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBackground(new Color(180, 220, 240));

        removeButton = UIHelper.createStyledButton("Видалити Завдання");
        completeButton = UIHelper.createStyledButton("Завершено");
        inProgressButton = UIHelper.createStyledButton("В процесі");
        notStartedButton = UIHelper.createStyledButton("Не розпочато");
        clearButton = UIHelper.createStyledButton("Очистити Все");
        sortButton = UIHelper.createStyledButton("Сортувати Завдання");
        editButton = UIHelper.createStyledButton("Редагувати Завдання");
        statsButton = UIHelper.createStyledButton("Статистика Завдань");
        JButton exitButton = UIHelper.createStyledButton("Вийти");

        exitButton.setBackground(new Color(255, 100, 100));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(removeButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(inProgressButton);
        buttonPanel.add(notStartedButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(editButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(exitButton);

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
