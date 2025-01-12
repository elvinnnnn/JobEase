# JobEase

JobEase is a mobile application designed to provide users with notifications of job offerings that match their preferences and qualifications.

<h>Parts</h>
<ul>
    <li>Scanning websites for job listings</li>
    <li>Storing website information in database</li>
    <li>User Application to view / query database</li>
    <li>Apply Automatically to Jobs</li>
</ul>

<h>Use Cases</h>
<ul>
    <li>Easy for lazy students to keep up with internship applications</li>
    <li>More Automation</li>
</ul>

<h>Software Architecture</h>
<h2>Model</h2>
<ul>
    <li>Handles all CRUD operations with Database</li>
</ul>
Classes:
<ul>
    <li>DataBaseConnection
    <li>Company
    <li>Application
    <li>JobOpportunity
    <li>JobSource
    <li>User

<h2>View</h2>
    <li>Dynamically loaded HTML</li>
    <li>Anything seen by the User</li>
    <li>Never requests Database directly</li>
<h2>Controller</h2>
    <li>Handles sending requests to database</li>
    <li>Tells database what it needs</li>
    <li>Renders/processes response from the database





