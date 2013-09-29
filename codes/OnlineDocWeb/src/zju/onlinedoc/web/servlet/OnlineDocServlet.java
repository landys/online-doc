package zju.onlinedoc.web.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zju.onlinedoc.web.command.ICommand;

/**
 * Servlet implementation class for Servlet: OnlineDocServlet
 * 
 */
public class OnlineDocServlet extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8601612699991989280L;
    
    /**
     * initial properties of the servlet, from web.xml
     */
    Properties initProps = new Properties();
    
    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public OnlineDocServlet()
    {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Servlet#destroy()
     */
    public void destroy()
    {
        // TODO Auto-generated method stub
        super.destroy();
    }

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null)
        {
            action = "default";
        }
        
        // Authorization management
        if (request.getSession().getAttribute("loginUserId") == null)
        {
            // Not login
            String strAuth = initProps.getProperty(action + "NeedAuthorization");
            if (!"false".equalsIgnoreCase(strAuth))
            {
                action = "login";
            }
        }
        
        System.out.println("Action is " + action + ".");
        
        // Execute the command
        String strCommand = initProps.getProperty(action + "Command");
        if (strCommand != null)
        {
            // Attribute executeResult is used to store the result of execute
            request.removeAttribute("executeResult");
            try
            {
                Class claCommand = Class.forName(strCommand);
                ICommand command = (ICommand) claCommand.newInstance();
                command.execute(request, response);
            }
            catch (ClassNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        // Forword to the right page
        String result = "";
        if (request.getAttribute("executeResult") != null)
        {
            result = (String)request.getAttribute("executeResult");
        }
        String strPage = initProps.getProperty(action + result + "Page");
        if (strPage != null)
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(strPage);
            dispatcher.forward(request, response);
        }
        
        response.setCharacterEncoding("UTF-8");
    }

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init()
     */
    public void init() throws ServletException
    {
        super.init();
        for (Enumeration en = getInitParameterNames(); en.hasMoreElements();)
        {
            String name = (String)en.nextElement();
            initProps.put(name, getInitParameter(name));
        }
    }
}