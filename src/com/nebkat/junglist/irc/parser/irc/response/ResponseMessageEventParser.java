/*
 * Copyright 2013 Nebojsa Cvetkovic. All rights reserved.
 *
 * This file is part of JunglistIRC.
 *
 * JunglistIRC is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JunglistIRC is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JunglistIRC.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nebkat.junglist.irc.parser.irc.response;

import com.nebkat.junglist.irc.Session;
import com.nebkat.junglist.irc.events.irc.IRCEvent;
import com.nebkat.junglist.irc.events.irc.response.InfoContentEvent;
import com.nebkat.junglist.irc.events.irc.response.InfoEndEvent;
import com.nebkat.junglist.irc.events.irc.response.InfoStartEvent;
import com.nebkat.junglist.irc.events.irc.response.MotdContentEvent;
import com.nebkat.junglist.irc.events.irc.response.MotdEndEvent;
import com.nebkat.junglist.irc.events.irc.response.MotdStartEvent;
import com.nebkat.junglist.irc.events.irc.response.ServerConnectedEvent;
import com.nebkat.junglist.irc.parser.ParseToken;
import com.nebkat.junglist.irc.parser.Parser;

public class ResponseMessageEventParser extends Parser {
    @Override
    public IRCEvent parse(long time, Session session, ParseToken token) {
        if (token.getParams().length < 2) {
            return null;
        }


        String message = token.getParams()[1];
        if (token.getCommand().equals(Parser.RESPONSE_MOTD_START)) {
            return new MotdStartEvent(time, session, token.getRaw(), token.getSource(), message);
        } else if (token.getCommand().equals(Parser.RESPONSE_MOTD_CONTENT)) {
            return new MotdContentEvent(time, session, token.getRaw(), token.getSource(), message);
        } else if (token.getCommand().equals(Parser.RESPONSE_MOTD_END)) {
            return new MotdEndEvent(time, session, token.getRaw(), token.getSource(), message);
        } else if (token.getCommand().equals(Parser.RESPONSE_INFO_START)) {
            return new InfoStartEvent(time, session, token.getRaw(), token.getSource(), message);
        } else if (token.getCommand().equals(Parser.RESPONSE_INFO_CONTENT)) {
            return new InfoContentEvent(time, session, token.getRaw(), token.getSource(), message);
        } else if (token.getCommand().equals(Parser.RESPONSE_INFO_END)) {
            return new InfoEndEvent(time, session, token.getRaw(), token.getSource(), message);
        } else if (token.getCommand().equals(Parser.RESPONSE_SERVER_CONNECTED)) {
            return new ServerConnectedEvent(time, session, token.getRaw(), token.getSource(), message);
        } else {
            return null;
        }
    }
}