import javax.swing.*;
import java.util.*;

public class TaskHandler {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JComboBox<String> priorityBox, statusBox;
    private Map<String, String> taskDetails;

    public TaskHandler(DefaultListModel<String> taskListModel, JList<String> taskList,
                       JTextField taskInput, JComboBox<String> priorityBox, JComboBox<String> statusBox) {
        this.taskListModel = taskListModel;
        this.taskList = taskList;
        this.taskInput = taskInput;
        this.priorityBox = priorityBox;
        this.statusBox = statusBox;
        this.taskDetails = new HashMap<>();
    }

    public void addTask() {
        String task = taskInput.getText().trim();
        String priority = (String) priorityBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();
        if (!task.isEmpty()) {
            String taskEntry = priority + " - " + status + " - " + task;
            taskListModel.addElement(taskEntry);
            taskDetails.put(taskEntry, "Деталі: " + task);
            taskInput.setText("");
        }
    }

    public void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedTask = taskListModel.getElementAt(selectedIndex);
            taskListModel.remove(selectedIndex);
            taskDetails.remove(selectedTask);
        }
    }

    public void updateTaskStatus(String status) {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedTask = taskListModel.getElementAt(selectedIndex);
            String[] parts = selectedTask.split(" - ", 3);
            if (parts.length == 3) {
                String updatedTask = parts[0] + " - " + status + " - " + parts[2];
                taskListModel.set(selectedIndex, updatedTask);
            }
        }
    }

    public void clearTasks() {
        taskListModel.clear();
        taskDetails.clear();
    }

    public void sortTasks() {
        List<String> tasks = new ArrayList<>();
        for (int i = 0; i < taskListModel.size(); i++) {
            tasks.add(taskListModel.get(i));
        }
        tasks.sort(Comparator.comparing(task -> task.startsWith("Високий") ? 1 : task.startsWith("Середній") ? 2 : 3));
        taskListModel.clear();
        tasks.forEach(taskListModel::addElement);
    }

    public void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedTask = taskListModel.getElementAt(selectedIndex);
            String[] parts = selectedTask.split(" - ", 3);
            if (parts.length == 3) {
                String newTask = JOptionPane.showInputDialog("Редагувати завдання:", parts[2]);
                if (newTask != null && !newTask.trim().isEmpty()) {
                    String updatedTask = parts[0] + " - " + parts[1] + " - " + newTask.trim();
                    taskListModel.set(selectedIndex, updatedTask);
                }
            }
        }
    }

    public void showStatistics() {
        JOptionPane.showMessageDialog(null, "Кількість завдань: " + taskListModel.size(),
                "Статистика", JOptionPane.INFORMATION_MESSAGE);
    }
}
