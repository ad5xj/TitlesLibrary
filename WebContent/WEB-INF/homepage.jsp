<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
        <meta charset="UTF-8">
        <meta name="description" content="WELCOME TO MyBooks WEB">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>MyBooks WEB</title>

        <!-- PAGE SPECIFIC LAYOUT -->
        <style>
            <!-- Homepage -->
            #base_container { width: 90%; text-align: center; }
            #bookshelf   { width: 40%; }
            #button_bar  { width: 10%; border-width: 1px; border-color: black; border-radius: 5px; vertical-align: top; }
            header { font-size: 1.9em; font-family: 'Liberation Serif'; line-height: 1.2em; }
            p      { font-size: 0.9em; line-height: 1.1em; }
            table  { margin-left: 20%; width: 50%; }
            td,
            img    { width:100%; }
            button
            {
                display: block;
                width: 90%;
                height: 40px;
                margin-top: 6px;
                color: black;
                border-radius: 5px;
                background-image: linear-gradient(#dedede, #fafafa);
                box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
            }
            button:hover { background-color: #008CBA; color: #ff3c3c; }
            footer { margin-left: 30%; font-size: 8pt; }
        </style>
  </head>

  <body>
    <div id="base_container">
      <header style="text-align: center;">MyBooks WEB version</header>
      <table>
        <tr>
          <td id="button_bar">
            <button type="submit" value="Exit">Exit</button>
            <button type="submit" value="TitlesMaint">Search</button>
            <button type="submit" value="PrintTitles">Print Titles</button>
            <button type="submit" value="PrintAuthors">Print Authors</button>
            <button type="submit" value="PrintMedia">Print Media</button>
            <button type="submit" value="Help">Help</button>
          </td>
          <td id="bookshelf">
            <img src="../Images/Bookshelf.png" alt="MyBooks"/>
          </td>
        </tr>
      </table>
      <footer>
        &copy; 2023 AD5XJ All rights reserved in the United States and elsewhere
      </footer>
    </div>
  </body>
</html>