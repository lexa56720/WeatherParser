package UI;

import DataTypes.CityInfo;
import Requester.SearchRequester;
import Requester.WeatherRequester;
import Utils.Event;
import Utils.IEventSubscriber;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.LinkedList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class SearchPanel extends JPanel implements IEventSubscriber<CityInfo>
{

    public Event<CityInfo> CitySelected=new Event<>();
    private JTextField SearchField = new JTextField();
    private JPanel SearchList = new JPanel();
    private LinkedList<SearchResult> Results = new LinkedList<>();

    public SearchPanel()
    {
        super();
        setLayout(new GridBagLayout());
        SetupSearchField();
        SetupList();
        revalidate();
    }

    private GridBagConstraints GetGrid()
    {
        var grid = new GridBagConstraints();
        grid.gridwidth = 1;
        grid.gridheight = 1;
        grid.weightx = 1;
        grid.weighty = 1;
        grid.gridy = 0;
        grid.gridx = 0;

        grid.fill = GridBagConstraints.BOTH;
        return grid;
    }
    private void SetupSearchField()
    {
        SearchField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override public void insertUpdate(DocumentEvent e)
            {
                UpdateSearchResults(SearchField.getText());
            }
            @Override public void removeUpdate(DocumentEvent e)
            {
                UpdateSearchResults(SearchField.getText());
            }
            @Override public void changedUpdate(DocumentEvent e)
            {
                UpdateSearchResults(SearchField.getText());
            }
        });
        SearchField.setFont(new Font(SearchField.getFont().getName(),Font.PLAIN, 18));
        var grid=GetGrid();
        grid.weighty=0.01;
        grid.anchor=GridBagConstraints.PAGE_START;
        grid.fill = GridBagConstraints.BOTH;
        add(SearchField,grid);
    }


    private void SetupList()
    {
        SearchList.setLayout(new BoxLayout(SearchList, BoxLayout.Y_AXIS));
        var scrollPane = new JScrollPane(SearchList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        var grid=GetGrid();
        grid.weighty=1;
        grid.gridy=1;
        add(scrollPane,grid);
    }

    private void UpdateSearchResults(String query)
    {
        if (query.length() <= 1)
            return;
        Clear();
        Add(SearchRequester.GetResult(query));
        revalidate();
    }

    private void Add(CityInfo[] cities)
    {
        for (var city : cities)
        {
            var component = new SearchResult(city);
            Results.add(component);
            SearchList.add(component);
            component.CitySelected.Subscribe(this);
        }
    }
    private void Clear()
    {
        for (var result : Results)
            result.CitySelected.UnSubscribe(this);
        Results.clear();
        SearchList.removeAll();
    }
    @Override public void EventRaised(CityInfo data, Object Sender)
    {
        CitySelected.NotifyAll(data,this);
    }
}
