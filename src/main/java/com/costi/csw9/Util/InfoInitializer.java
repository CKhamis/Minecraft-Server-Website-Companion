package com.costi.csw9.Util;

import com.costi.csw9.Model.Ajax.ProjectInfo;

import java.util.ArrayList;
import java.util.List;

public class InfoInitializer {
    public static List<ProjectInfo> initializeProjects() {
        List<ProjectInfo> objects = new ArrayList<>();

        // Create objects
        objects.add(new ProjectInfo("interdimensional", "python", "", "/images/webpageImages/CS-122.png", "Computer Science 122 Projects", true, "School Collaborative Projects | 2022",  "These are a collection of Python projects I have made for my Python class in SJSU most of these are projects I have collaborated with. The GitHub repositories listed here may not contain the final code we used to turn in, as there were some syncing issues with our code. Some highlights of the class were using tkinter to make a matching game and a simple web server using django and flask.", new String[]{"https://github.com/CKhamis/CS-122-Homework-2", "https://github.com/CKhamis/CS-122-Homework-4", "https://github.com/CKhamis/CS-122-Homework-6", "https://github.com/CKhamis/CS-122-Homework-7", "https://github.com/CKhamis/CS-122-Homework-8"}));
        objects.add(new ProjectInfo("interdimensional", "java", "", "/images/webpageImages/CostiOnline.jpg", "Costi Online", false, "Personal Project | 2022", "Costi Online is a website that is designed to show off various projects, services, and media I made. This project initially began as a website that covers my 9th Minecraft survival world. It had links to participate in elections, polls, and the history of the previous worlds. After it was published on costionline.com, I decided to make it more general. The aforementioned feature has been moved to the <a href=\"/Minecraft\">Minecraft</a> section.", new String[]{"https://github.com/CKhamis/Costi-Online-Website"}));
        objects.add(new ProjectInfo("interdimensional", "java", "CIPolarium", "/images/webpageImages/Polarium.png", "Constantine [IN]GRID Polarium", true, "Personal Project | 2021", "This is a personal project I made that keeps track of various relationships I have made similar to a journal. I used Java Spring and Hibernate for this project, much like Costi Online. This project was discontinued because of complications with making a secure account system (which is very important for a personal journaling program). This program was able to add, edit, and remove people. Each person had their own database for tracking events.", new String[]{"https://github.com/CKhamis/Constantine-INGRID-Polarium"}));
        objects.add(new ProjectInfo("interdimensional", "java", "CNGrid", "/images/webpageImages/NextGrid.jpg", "Constantine [NEXT]GRID", true, "Private Repository | 2021", "This project was quite similar to [IN]GRID Polarium in terms of purpose. They both were designed to be a personal diary. However, this project used Java Swing as its interface and used CSV files for data persistence. There were no third party libraries used in this program, so I made my own CSV reader and writer classes myself. The project was discontinued due to frustrations with Java Swing and persistent bugs and limitations with displaying information. The GitHub repository is private because it contains a CSV file with personal information.", new String[]{}));
        objects.add(new ProjectInfo("interdimensional", "java", "", "/images/webpageImages/BankingApp.png", "Banking App", true, "School Collaborative Project | 2021", "\"Our banking app was created using the Java Swing library and an MVC pattern with event driven programming. The application allows for multiple users. Each user has their own account and have different bank accounts such as checkings and savings. The user is able to withdraw, deposit, and transfer to and from each account. In addition, the user has a Credit account where they can view thier credit card balance and pay off their credit card bill.\" - Sunny Chen, a collaborator", new String[]{"https://github.com/CKhamis/banking-app"}));
        objects.add(new ProjectInfo("pseudo-industrial", "java", "", "/images/webpageImages/SCQG.jpg", "Smart Console Query Generator", true, "Personal Project | 2020", "This project was made to help me automate a frequently repeating task of my job as an intern in the Cyber Security team of San Jose. This project was meant to create queries for the CheckPoint Smart Console application.", new String[]{"https://github.com/CKhamis/SmartConsoleQueryGenerator"}));
        objects.add(new ProjectInfo("pseudo-industrial", "java", "", "/images/webpageImages/Polaric.jpg", "Constantine [IN]GRID Polaric", true, "Private Repository | 2020", "This was an application designed to analyze spreadsheet journals. It could recognize different versions of the CSV files and show you differences between them, analyze the data in them so you can make better decisions, and more. It was quite an advanced program that took a few months to develop. The project was discontinued because I did not know how to make a GUI for it. This project contains personal information, so the repository is private.", new String[]{}));
        objects.add(new ProjectInfo("pseudo-industrial", "java", "", "/images/webpageImages/FGSA.jpg", "Five Guys Schedule Analyzer", true, "Personal Project | 2020", "This project was meant to take schedules I receive from Five Guys as an input and give me helpful analytical information. It used my CSV reader file to analyze each schedule and locate valuable information. Discontinued because I did not know of any good way of making a GUI.", new String[]{"https://github.com/CKhamis/Five-Guys-Schedule-Analyzer"}));
        objects.add(new ProjectInfo("pseudo-industrial", "web", "CIPolaris", "/images/webpageImages/Polaris.jpg", "Constantine [IN]GRID Polaris", true, "Personal Project | 2019", "This project was mainly developed using Javascript D3.js to populate data on webpages from CSV files. The purpose of this program is quite similar to what came after it, which is to display journaling information. The project was discontinued because I could not figure out how I would turn this into a functioning server in the long run. There are no public repositories for this project because the CSV it uses contains personal information.", new String[]{}));
        objects.add(new ProjectInfo("pseudo-industrial", "web", "", "/images/webpageImages/CostiInc.jpg", "Costi Inc.", true, "Personal Project | 2018", "This project is a website I made in 2018 that shared a very similar purpose to this website. It featured the different projects and video channels I was posting to at the time. It utilized bootstrap and was hand coded by me.", new String[]{"https://github.com/CKhamis/Costi-Inc"}));
        objects.add(new ProjectInfo("highschool", "web", "", "/images/webpageImages/CR.jpg", "Costi Resume", true, "Personal Project | 2017", "Codename project Velocity. This is a resume website I made using HTML, CSS, and JavaScript. I was about 15 or 16 when I made this. I made this during the time I was learning D3.js", new String[]{"https://github.com/CKhamis/Resume-Website"}));
        objects.add(new ProjectInfo("highschool", "web", "", "/images/webpageImages/InfiniteID.jpg", "Constantine Infinite ID", true, "Personal Project | 2017", "This was the first diary/journaling program I created for myself. It was a website that used D3.JS to pull information from a CSV file that contained all the information needed. The project was discontinued due to bugs that I could not remove at the time.", new String[]{"https://github.com/CKhamis/Infinite-ID"}));
        objects.add(new ProjectInfo("highschool", "web", "", "/images/webpageImages/RoboticsWebsite3.png", "Pioneer Robotics Team Website (Milestone 3)", true, "Club Project | 2017", "This was a new project I wanted to make for my robotics team. I built it from scratch and I wanted to avoid the design misdirections its predecessors have taken (because of my team manager's preferences) while also applying feedback I heard about the current website. This website is unfinished because during its initial development, my team's manager suggested I should use a website builder instead. Knowing the countless hours and energy I put into this project (and previous robotics webpages) I quit.", new String[]{"https://github.com/CKhamis/https-github.com-CKhamis-Robotics-Website-Milestone-3"}));
        objects.add(new ProjectInfo("highschool", "web", "", "/images/webpageImages/RoboticsWebsite2.png", "Pioneer Robotics Team Website (Milestone 2)", true, "Club Project | 2017", "This was an iteration of the previous milestone 1 robotics page. It had more features, simpler and easier to navigate pages, and more Java Script integration. It even featured the game snake on the 404 page. It was seemingly generally liked among my team.", new String[]{"https://github.com/CKhamis/Robotics-Website-Milestone-2"}));
        objects.add(new ProjectInfo("highschool", "web", "", "/images/webpageImages/RoboticsWebsite.jpg", "Pioneer Robotics Team Website (Milestone 1)", true, "Club Project | 2016", "After making a handful of websites for my robotics team (each being rejected at their final development stages) I decided to have the team manager chose what elements he wanted in the team website. He ended up choosing this website design, which was not great in my personal opinion, but I was able to get this published. I made all the assets that went into this website and hand coded everything.", new String[]{"https://github.com/CKhamis/Resume-Website"}));
        objects.add(new ProjectInfo("highschool", "web", "", "/images/webpageImages/Acceleration.png", "Pioneer Robotics Team Website (Codename: Acceleration)", true, "Club Project | 2015", "This was a website I made over the summer 2015 break in my freshman year. I created a colorful, eye-catching website for my robotics team. I made it entirely from HTML and CSS, created my own assets, and designed the layout myself. I do not have a backup of this project unfortunately and this screenshot is all I have of it. This project was promptly rejected during the first week of school.", new String[]{}));
        objects.add(new ProjectInfo("highschool", "web", "", "/images/webpageImages/MWC.jpg", "Pioneer Robotics Team Mobile Website C", true, "Club Project | 2015", "At the time, I was very proud of what I have made. I created a more consistent interface, invented the idea of team skill \"categories,\" with their own individual informative webpages, and displayed all information on our desktop website in a mobile friendly way. This was before I learned bootstrap well. This is not the final version of the project, as I distinctly remember placing JavaScript Easter eggs which are not present in the version I uploaded.", new String[]{"https://github.com/CKhamis/Robotics-Mobile-Website-C"}));
        objects.add(new ProjectInfo("highschool", "web", "", "/images/webpageImages/RoboticsMobileWebsite.jpg", "Pioneer Robotics Team Mobile Website", true, "Club Project | 2015", "When I joined my high school robotics team, I was asked by my web captain to create a mobile counterpart to our desktop only site. I eventually came up with this project. It featured javascript easter eggs and phone-specific styling. I used only HTML, CSS, and Java Script to create my project without any frameworks. The version pictured and saved to GitHub is a very early version of the project. I had since made other versions that look completely different.", new String[]{"https://github.com/CKhamis/Robotics-Mobile-Website"}));
        objects.add(new ProjectInfo("childhood", "web", "", "/images/webpageImages/Birds.jpg", "Original Angry Birds Wiki", true, "Personal Project | 2014", "This project is a website dedicated to the topic of Angry Birds, a game I liked when I was little. I hand coded this entire website using HTML and CSS with no frameworks.", new String[]{"https://github.com/CKhamis/Angrybird-Wiki"}));
        objects.add(new ProjectInfo("childhood", "web", "", "/images/webpageImages/OriginalCostiWebsite.jpg", "Original Laptoop Website", true, "Personal Project | 2013", "I made this website when I was first learning web development in 2013. At the time, I was 12. This website featured a product page for a made up laptop of the made up brand \"laptoop,\" which I came up with in third grade.", new String[]{"https://github.com/CKhamis/Laptoop-Company-Website"}));
        return objects;
    }
}
