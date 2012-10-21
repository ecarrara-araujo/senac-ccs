<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Think Fast Game</title>
        <script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="resources/js/knockout-2.0.0.js"></script>
        <script type="text/javascript" src="resources/js/knockout.mapping-latest.js"></script>
    </head>
    <body>
        <h1>Think Fast Game</h1>
        <div id="participant">
            <h2>Insert your name and click start to begin:</h2>
            <input type="text" name="participant" data-bind="value: participant"/>
            <input type="button" value="start" data-bind="click: play" />
        </div>
        <br/>
        <ul data-bind="foreach: participants">
            <li style="list-style: none;">
                <span data-bind="text: $data.name">Joe</span>
                - <span data-bind="text: $data.score">0</span>
            </li>
        </ul>
        <div id="survey" data-bind="withparticipants: question">
            <span data-bind="text: description">Qual a capital da Rússia?</span>
            <ul data-bind="foreach: answers">
                <li style="list-style: none;">
                    <input type="radio" name="answer" data-bind="click: $root.answer"/>
                    <span data-bind="text: $data.description">Moscou</span>
                </li>
            </ul>
        </div>
        <span id="message" data-bind="text: message"></span>
        <script>
            var ThinkFast = function() {
                var self = this;
                self.participant = ko.observable();
                self.question = ko.observable();
                self.message = ko.observable();
                self.participants = ko.observableArray([]);
                
                self.play = function() {
                    $.getJSON("/thinkfast/play", {name: self.participant()}, function(data) {
                        ko.mapping.fromJS(data, {}, self);
                        self.bind();
                    });
                }
                
                self.bind = function() {
                    $.getJSON("/thinkfast/bind", function(data) {
                        ko.mapping.fromJS(data, {}, self);
                    }).complete(function() {
                        self.bind();
                    });
                }
                
                self.answer = function(answer) {
                    $.ajax({ url: "${pageContext.servletContext.contextPath}/thinkfast/answer",
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify(ko.mapping.toJS(answer)),
                        contentType: "application/json; charset=utf-8",
                        traditional: true,
                        success: function(data) {
                            ko.mapping.fromJS(data, {}, self);
                        }})
                    return true;
                }
            }
            ko.applyBindings(new ThinkFast());
        </script>
    </body>
</html>