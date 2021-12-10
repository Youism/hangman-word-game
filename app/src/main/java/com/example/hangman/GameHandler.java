package com.example.hangman;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This program represents the hangman game handler
 */
public class GameHandler extends AppCompatActivity {


    static int currentGameStreak = 0; // win streak

    static boolean areSoundsOn = true;

    static String currentCustomWord = "";


    static HashMap<String, ArrayList<String>> gameWordDataCache = new HashMap<String, ArrayList<String>>(); // words found

    // English data
    static HashMap<String, ArrayList<String>> gameWordData = new HashMap<String, ArrayList<String>>() {
        {
            put("CET4", new ArrayList<String>() {
                {

                    add("puzzle");
                    add("role");
                    add("complicate");
                    add("economic");
                    add("transition");
                    add("wisdom");
                    add("medical");
                    add("among");
                    add("arrangement");
                    add("participate");
                    add("challenge");
                    add("organize");
                    add("essay");
                    add("institution");
                    add("control");
                    add("concern");
                    add("significantly");
                    add("growth");
                    add("competition");
                    add("educator");
                    add("tend");
                    add("organisation");
                    add("bacterium");
                    add("connection");
                    add("consumer");
                    add("survey");
                    add("psychological");
                    add("combination");
                    add("effective");
                    add("imply");
                    add("position");
                    add("psychologist");
                    add("instruction");
                    add("process");
                    add("philosopher");
                    add("type");
                    add("extraordinary");
                    add("economy");
                    add("mental");
                    add("center");
                    add("effectively");
                    add("circumstance");
                    add("employee");
                    add("innovation");
                    add("communicate");
                    add("partly");
                    add("mind-set");
                    add("impact");
                    add("alternative");
                    add("average");
                    add("facilitate");
                    add("society");
                    add("administration");
                    add("attribute");
                    add("design");
                    add("potential");
                    add("manufacturer");
                    add("support");
                    add("interference");
                    add("unfortunately");
                    add("jam");
                    add("administrative");
                    add("shift");
                    add("trend");
                    add("productivity");
                    add("tip");
                    add("rank");
                    add("innovator");
                    add("save");
                    add("tear");
                    add("competitive");
                    add("restriction");
                    add("irregular");
                    add("individual");
                    add("determine");
                    add("case");
                    add("expect");
                    add("contribution");
                    add("investigate");
                    add("obesity");
                    add("beep");
                    add("highlight");
                    add("curiosity");
                    add("self-drive");
                    add("project");
                    add("prove");
                    add("teamwork");
                    add("digital");
                    add("respondent");
                    add("apartment");
                    add("alcohol");
                    add("exist");
                    add("profession");
                    add("physical");
                    add("require");
                    add("discipline");
                    add("term");
                    add("characterize");
                    add("indeed");
                    add("matter");
                    add("insight");
                    add("independence");
                    add("expression");
                    add("goal");
                    add("explanation");
                    add("rate");
                    add("estimate");
                    add("tax");
                    add("software");
                    add("perspective");

                }
            });
            put("CET6", new ArrayList<String>() {
                {
                    add("technology");
                    add("environment");
                    add("competition");
                    add("energy");
                    add("economic");
                    add("unemployment");
                    add("author");
                    add("application");
                    add("rate");
                    add("institution");
                    add("correspond");
                    add("system");
                    add("consumer");
                    add("increasingly");
                    add("global");
                    add("advertise");
                    add("benefit");
                    add("management");
                    add("issue");
                    add("traditional");
                    add("performance");
                    add("provide");
                    add("immigration");
                    add("program");
                    add("relationship");
                    add("robot");
                    add("homeless");
                    add("accord");
                    add("nation");
                    add("significant");
                    add("conversation");
                    add("unite");
                    add("agriculture");
                    add("society");
                    add("primary");
                    add("concern");
                    add("support");
                    add("professional");
                    add("firm");
                    add("economy");
                    add("researcher");
                    add("interactive");
                    add("participate");
                    add("product");
                    add("agricultural");
                    add("industry");
                    add("among");
                    add("participant");
                    add("report");
                    add("educational");
                    add("course");
                    add("manufacturing");
                    add("online");
                    add("improvement");
                    add("professor");
                    add("productivity");
                    add("consequence");
                    add("produce");
                    add("responsibility");
                    add("challenge");
                    add("view");
                    add("achievement");
                    add("rise");
                    add("transportation");
                    add("organization");
                    add("psychological");
                    add("require");
                    add("possibility");
                    add("impact");
                    add("psychologist");
                    add("income");
                    add("demand");
                    add("sustainable");
                    add("measure");
                    add("association");
                    add("administration");
                    add("climate");
                    add("decade");
                    add("craftsmanship");
                    add("graduate");
                    add("alternative");
                    add("effect");
                    add("competitive");
                    add("resource");
                    add("responsible");
                    add("interaction");
                    add("average");
                    add("survey");
                    add("staff");
                    add("politician");
                    add("raise");
                    add("knowledge");
                    add("cite");
                    add("academic");
                    add("effectively");
                    add("process");
                    add("annual");
                    add("particularly");
                    add("direction");
                    add("case");
                    add("differently");
                    add("focus");
                    add("temperature");
                    add("description");
                    add("million");
                    add("generation");
                    add("relatively");
                    add("technological");
                    add("participation");
                    add("advance");
                    add("device");
                    add("middle-class");
                    add("revolutionary");
                    add("expect");
                    add("argue");
                    add("explanation");
                    add("reduce");
                    add("sociologist");
                    add("undergraduate");
                    add("statement");
                    add("power");
                    add("potentially");
                    add("characteristic");
                    add("employee");
                    add("credit");
                    add("appropriate");
                    add("meaningful");
                    add("happiness");
                    add("political");
                    add("unfortunately");
                    add("consumption");
                    add("design");
                    add("environmentalist");
                    add("distinguish");
                    add("demonstrate");
                    add("access");
                    add("basis");
                    add("current");
                    add("department");
                    add("solution");
                    add("behavior");
                    add("prompt");
                    add("media");
                    add("intellectual");
                    add("emission");
                    add("bureau");
                    add("tend");
                    add("communicate");
                    add("payroll");
                    add("sheet");
                    add("poverty");
                    add("preference");
                    add("save");
                    add("tenant");
                    add("living");
                    add("incandescent");
                    add("politically");
                    add("major");
                    add("gain");
                    add("blame");
                    add("percentage");
                    add("simply");
                    add("perspective");
                    add("term");
                    add("broadband");
                    add("surprisingly");
                    add("growth");
                    add("community");
                    add("pension");
                    add("scientific");
                    add("engineering");
                    add("activity");
                }
            });
            put("专八", new ArrayList<String>() {
                {
                    add("paragraph");
                    add("environment");
                    add("opportunity");
                    add("author");
                    add("organization");
                    add("system");
                    add("firm");
                    add("failure");
                    add("relationship");
                    add("among");
                    add("traditional");
                    add("employee");
                    add("instruction");
                    add("provide");
                    add("award");
                    add("economic");
                    add("view");
                    add("responsibility");
                    add("sheet");
                    add("increasingly");
                    add("power");
                    add("underline");
                    add("society");
                    add("appropriate");
                    add("opinion");
                    add("personality");
                    add("requirement");
                    add("content");
                    add("perspective");
                    add("expect");
                    add("institution");
                    add("reunion");
                    add("relevance");
                    add("quality");
                    add("sufficiency");
                    add("concern");
                    add("performance");
                    add("design");
                    add("significant");
                    add("professional");
                    add("describe");
                    add("suit");
                    add("activity");
                    add("satisfaction");
                    add("career");
                    add("imagination");
                    add("living");
                    add("environmental");
                    add("raise");
                    add("support");
                    add("bilingualism");
                    add("previous");
                    add("build");
                    add("contribution");
                    add("course");
                    add("security");
                    add("achievement");
                    add("benefit");
                    add("conservation");
                    add("undergraduate");
                    add("urban");
                    add("marriage");
                    add("particularly");
                    add("association");
                    add("generation");
                    add("require");
                    add("offering");
                    add("possibility");
                    add("ambition");
                    add("neighbourhood");
                    add("naked");
                    add("flexible");
                    add("agricultural");
                    add("role");
                    add("flush");
                    add("online");
                    add("interviewee");
                    add("resource");
                    add("history");
                    add("appearance");
                    add("appreciate");
                    add("million");
                    add("emphasis");
                    add("reflect");
                    add("industry");
                    add("servant");
                    add("rise");
                    add("manufacturer");
                    add("graduate");
                    add("manufacturing");
                    add("efficiency");
                    add("challenge");
                    add("physical");
                    add("twist");
                    add("landscape");
                    add("cognitive");
                    add("excerpt");
                    add("formal");
                    add("tribe");
                    add("culture");
                    add("economy");
                    add("fundamental");
                    add("independent");
                    add("energy");
                    add("frontier");
                    add("approach");
                    add("interference");
                    add("asset");
                    add("statement");
                    add("loss");
                    add("expertise");
                    add("matter");
                    add("actual");
                    add("issue");
                    add("accommodation");
                    add("bilingual");
                    add("consumption");
                    add("forever");
                    add("reduce");
                    add("productivity");
                    add("translate");
                    add("intervention");
                    add("craft");
                    add("knowledge");
                    add("former");
                    add("newcomer");
                    add("rent");
                    add("advantage");
                    add("lack");
                    add("composition");
                    add("indifferent");
                    add("deal");
                    add("perfection");
                    add("implication");
                    add("unite");
                    add("advertise");
                    add("consequence");
                    add("crowd");
                    add("retire");
                    add("reward");
                    add("expectation");
                    add("argument");
                    add("responsible");
                    add("global");
                    add("novel");
                    add("employer");
                    add("freedom");
                    add("thus");
                    add("magnificent");
                    add("globe");
                    add("case");
                    add("suburb");
                    add("retirement");
                    add("base");
                    add("tie");
                    add("bondage");
                }
            });
            put("专四", new ArrayList<String>() {
                {
                    add("item");
                    add("opportunity");
                    add("author");
                    add("traditional");
                    add("phrase");
                    add("composition");
                    add("award");
                    add("statement");
                    add("refer");
                    add("tax");
                    add("possibility");
                    add("grammar");
                    add("local");
                    add("provide");
                    add("civilization");
                    add("appropriateness");
                    add("express");
                    add("appropriate");
                    add("technology");
                    add("loss");
                    add("smoking");
                    add("vocabulary");
                    add("system");
                    add("dictation");
                    add("stress");
                    add("fiction");
                    add("site");
                    add("produce");
                    add("society");
                    add("relationship");
                    add("marriage");
                    add("incorrect");
                    add("device");
                    add("detail");
                    add("rate");
                    add("disappoint");
                    add("situation");
                    add("indicate");
                    add("concern");
                    add("interrupt");
                    add("control");
                    add("competition");
                    add("carefully");
                    add("professional");
                    add("immigrant");
                    add("personal");
                    add("taxation");
                    add("knowledge");
                    add("association");
                    add("million");
                    add("graduate");
                    add("increasingly");
                    add("paragraph");
                    add("indifferent");
                    add("major");
                    add("engage");
                    add("entertainment");
                    add("correspond");
                    add("type");
                    add("artist");
                    add("effect");
                    add("reading");
                    add("character");
                    add("interval");
                    add("graduation");
                    add("habit");
                    add("appearance");
                    add("instruction");
                    add("role");
                    add("support");
                    add("income");
                    add("culture");
                    add("inform");
                    add("product");
                    add("disturb");
                    add("figure");
                    add("focus");
                    add("recession");
                    add("event");
                    add("content");
                    add("mummy");
                    add("supervisor");
                    add("describe");
                    add("grant");
                    add("view");
                    add("specifically");
                    add("course");
                    add("decline");
                    add("arrange");
                    add("advance");
                    add("attachment");
                    add("immigration");
                    add("report");
                    add("labor");
                    add("hesitant");
                    add("contemporary");
                    add("welfare");
                    add("evil");
                    add("context");
                    add("comment");
                    add("literary");
                    add("involve");
                    add("extra");
                    add("symbol");
                    add("underwater");
                    add("despite");
                    add("writer");
                    add("presentation");
                    add("opinion");
                    add("obvious");
                    add("catch");
                    add("actual");
                    add("emotion");
                    add("educational");
                    add("ancient");
                    add("operation");
                    add("communicate");
                    add("nowadays");
                    add("production");
                    add("interviewee");
                    add("stage");
                    add("movement");
                    add("particularly");
                    add("rescue");
                    add("medical");
                    add("aside");
                    add("design");
                    add("symptom");
                    add("conference");
                    add("power");
                    add("eventually");
                    add("expect");
                    add("engine");
                    add("handle");
                    add("elderly");
                    add("addition");
                    add("responsibility");
                    add("freedom");
                    add("message");
                    add("carpenter");
                    add("adverbial");
                    add("deal");
                    add("informal");
                    add("amount");
                    add("achievement");
                    add("headphone");
                    add("solve");
                    add("performance");
                    add("usage");
                    add("attempt");
                }
            });
            put("GRE", new ArrayList<String>() {
                {
                    add("conventional");
                    add("describe");
                    add("unite");
                    add("alternative");
                    add("median");
                    add("particular");
                    add("claim");
                    add("demonstrate");
                    add("particularly");
                    add("accord");
                    add("controversial");
                    add("history");
                    add("environmental");
                    add("period");
                    add("renaissance");
                    add("historical");
                    add("characteristic");
                    add("novel");
                    add("distinguish");
                    add("primarily");
                    add("scientific");
                    add("characterize");
                    add("architecture");
                    add("system");
                    add("intellectual");
                    add("writer");
                    add("argue");
                    add("terrestrial");
                    add("organization");
                    add("historian");
                    add("scholarship");
                    add("individual");
                    add("astronomer");
                    add("scholar");
                    add("achievement");
                    add("account");
                    add("composition");
                    add("critic");
                    add("infer");
                    add("possibility");
                    add("distinction");
                    add("researcher");
                    add("concern");
                    add("promote");
                    add("produce");
                    add("manufacturing");
                    add("contribution");
                    add("apply");
                    add("region");
                    add("biodiversity");
                    add("nevertheless");
                    add("select");
                    add("description");
                    add("opportunity");
                    add("sentence");
                    add("anticipate");
                    add("native");
                    add("subtle");
                    add("behavior");
                    add("complicate");
                    add("identify");
                    add("significance");
                    add("literary");
                    add("experimental");
                    add("climate");
                    add("material");
                    add("revolutionary");
                    add("investigate");
                    add("community");
                    add("link");
                    add("contrast");
                    add("reader");
                    add("traditionally");
                    add("confuse");
                    add("occur");
                    add("impact");
                    add("consequence");
                    add("agricultural");
                    add("process");
                    add("challenge");
                    add("archaeologist");
                    add("hypothesis");
                    add("competition");
                    add("considerable");
                    add("source");
                    add("culture");
                    add("contrary");
                    add("participation");
                    add("highlight");
                    add("architectural");
                    add("activity");
                    add("design");
                    add("scene");
                    add("unpredictable");
                    add("surface");
                    add("position");
                    add("image");
                    add("constitute");
                    add("planet");
                    add("movement");
                    add("concentration");
                    add("impressionist");
                    add("factor");
                    add("stage");
                    add("base");
                    add("increasingly");
                    add("influence");
                    add("role");
                    add("familiar");
                    add("enhance");
                    add("discuss");
                    add("term");
                    add("substantial");
                    add("discrimination");
                    add("chimpanzee");
                    add("cultivation");
                    add("approach");
                    add("insurrection");
                    add("phenomenon");
                    add("irrelevant");
                    add("require");
                    add("practical");
                    add("conclusion");
                    add("opinion");
                    add("function");
                    add("philosophical");
                    add("assumption");
                    add("controversy");
                    add("nature");
                    add("orientation");
                    add("theoretical");
                    add("context");
                    add("illustration");
                    add("subject");
                    add("implication");
                    add("decline");
                    add("ancient");
                    add("productivity");
                    add("metamorphosis");
                    add("statement");
                    add("comprehensive");
                    add("predictable");
                    add("compromise");
                    add("primary");
                    add("feature");
                    add("journal");
                    add("consideration");
                    add("determine");
                    add("politician");
                    add("establish");
                    add("artist");
                    add("underestimate");
                    add("photography");
                    add("generally");
                    add("personality");
                    add("lack");
                    add("nonetheless");
                    add("economic");
                    add("ambition");
                    add("tend");
                    add("event");
                    add("emphasis");
                    add("ecosystem");
                    add("misunderstand");
                }
            });
            put("考研", new ArrayList<String>() {
                {
                    add("association");
                    add("media");
                    add("executive");
                    add("translation");
                    add("economic");
                    add("organization");
                    add("data");
                    add("institution");
                    add("power");
                    add("system");
                    add("professional");
                    add("increasingly");
                    add("society");
                    add("rate");
                    add("require");
                    add("publication");
                    add("court");
                    add("comprehension");
                    add("researcher");
                    add("responsibility");
                    add("journal");
                    add("performance");
                    add("measure");
                    add("campaign");
                    add("case");
                    add("federal");
                    add("possibility");
                    add("manufacturing");
                    add("major");
                    add("intelligence");
                    add("view");
                    add("journalist");
                    add("accord");
                    add("significant");
                    add("generation");
                    add("influence");
                    add("base");
                    add("archaeologist");
                    add("effect");
                    add("influential");
                    add("report");
                    add("provide");
                    add("productivity");
                    add("individual");
                    add("commission");
                    add("relationship");
                    add("paragraph");
                    add("issue");
                    add("undergraduate");
                    add("among");
                    add("statistical");
                    add("ignore");
                    add("site");
                    add("decline");
                    add("argue");
                    add("pressure");
                    add("contribute");
                    add("claim");
                    add("particularly");
                    add("digital");
                    add("advertise");
                    add("product");
                    add("anthropologist");
                    add("fashion");
                    add("suitable");
                    add("immigration");
                    add("financial");
                    add("authority");
                    add("account");
                    add("scientific");
                    add("administration");
                    add("vote");
                    add("deal");
                    add("agency");
                    add("unite");
                    add("competition");
                    add("process");
                    add("traditional");
                    add("focus");
                    add("conscious");
                    add("humanity");
                    add("fundamental");
                    add("history");
                    add("consumption");
                    add("benefit");
                    add("circumstance");
                    add("concern");
                    add("hack");
                    add("consumer");
                    add("precisely");
                    add("demand");
                    add("enhance");
                    add("community");
                    add("archaeological");
                    add("technological");
                    add("robot");
                    add("support");
                    add("statistics");
                    add("rise");
                    add("changing");
                    add("application");
                    add("million");
                    add("replace");
                    add("living");
                    add("matter");
                    add("grammar");
                    add("discipline");
                    add("official");
                    add("average");
                    add("damage");
                    add("firm");
                    add("consequence");
                    add("corporation");
                    add("decade");
                    add("responsible");
                    add("fund");
                    add("course");
                    add("admit");
                    add("survey");
                    add("enforcement");
                    add("political");
                    add("engage");
                    add("demonstrate");
                    add("knowledge");
                    add("constitution");
                    add("nature");
                    add("scholar");
                    add("search");
                    add("collection");
                    add("security");
                    add("anthropology");
                    add("massive");
                    add("growth");
                    add("construction");
                    add("access");
                    add("environmental");
                    add("justice");
                    add("assess");
                    add("refer");
                    add("approach");
                    add("carefully");
                    add("well-being");
                    add("graduate");
                    add("meaningful");
                    add("industry");
                    add("approval");
                    add("apply");
                    add("integrity");
                    add("coherent");
                    add("technology");
                    add("user");
                    add("celebrate");
                    add("mental");
                    add("kingdom");
                    add("competitive");
                    add("energy");
                    add("customer");
                    add("operate");
                    add("practical");
                    add("patent");
                    add("economy");
                    add("interaction");
                    add("impact");
                    add("adjust");
                    add("conservation");
                    add("challenge");
                    add("global");
                    add("perspective");
                    add("define");
                    add("acknowledge");
                    add("earn");
                    add("attitude");
                    add("recruit");
                    add("segment");
                    add("entrepreneur");
                    add("promote");
                    add("implication");
                    add("gap");
                    add("critical");
                    add("amazon");
                    add("master");
                    add("author");
                    add("theory");
                    add("disorder");
                }
            });
            put("编程", new ArrayList<String>() {
                {
                    add("attribute");
                    add("raise");
                    add("instance");
                    add("support");
                    add("exception");
                    add("specify");
                    add("error");
                    add("provide");
                    add("parameter");
                    add("byte");
                    add("define");
                    add("format");
                    add("message");
                    add("contain");
                    add("character");
                    add("option");
                    add("chapter");
                    add("import");
                    add("variable");
                    add("encode");
                    add("process");
                    add("system");
                    add("base");
                    add("socket");
                    add("source");
                    add("log");
                    add("sequence");
                    add("expression");
                    add("package");
                    add("event");
                    add("dictionary");
                    add("context");
                    add("header");
                    add("case");
                    add("handler");
                    add("server");
                    add("output");
                    add("integer");
                    add("statement");
                    add("false");
                    add("directory");
                    add("mode");
                    add("current");
                    add("handle");
                    add("implement");
                    add("element");
                    add("optional");
                    add("program");
                    add("implementation");
                    add("interface");
                    add("protocol");
                    add("buffer");
                    add("command");
                    add("user");
                    add("item");
                    add("interpreter");
                    add("operation");
                    add("parse");
                    add("access");
                    add("loop");
                    add("software");
                    add("application");
                    add("represent");
                    add("block");
                    add("request");
                    add("input");
                    add("execute");
                    add("subclass");
                    add("binary");
                    add("keyword");
                    add("require");
                    add("previous");
                    add("extension");
                    add("convert");
                    add("built-in");
                    add("parser");
                    add("entry");
                    add("documentation");
                    add("filename");
                    add("generator");
                    add("stream");
                    add("remove");
                    add("mock");
                    add("environment");
                    add("iterator");
                    add("equivalent");
                    add("descriptor");
                    add("local");
                    add("float");
                    add("warning");
                    add("connection");
                    add("describe");
                    add("global");
                    add("namespace");
                    add("detail");
                    add("script");
                    add("install");
                    add("index");
                    add("control");
                    add("indicate");
                    add("structure");
                    add("definition");
                    add("content");
                    add("load");
                    add("callback");
                    add("hash");
                    add("correspond");
                    add("length");
                    add("debug");
                    add("logger");
                    add("platform");
                    add("replace");
                    add("search");
                    add("signal");
                    add("constructor");
                    add("client");
                    add("multiple");
                    add("exit");
                    add("ignore");
                    add("display");
                    add("feature");
                    add("modify");
                    add("directly");
                    add("decode");
                    add("document");
                    add("additional");
                    add("specific");
                    add("constant");
                    add("limit");
                    add("deprecate");
                    add("occur");
                    add("generate");
                    add("array");
                    add("custom");
                    add("decimal");
                    add("copyright");
                    add("asynchronous");
                    add("callable");
                    add("stack");
                    add("range");
                    add("pointer");
                    add("compile");
                    add("expect");
                    add("operate");
                    add("license");
                    add("syntax");
                    add("usage");
                    add("prefix");
                    add("determine");
                    add("bind");
                    add("override");
                    add("configuration");
                    add("literal");
                    add("representation");
                    add("exist");
                    add("collection");
                    add("queue");
                    add("info");
                    add("finder");
                    add("regular");
                    add("host");
                    add("particular");
                    add("automatically");
                    add("refer");
                    add("operator");
                    add("produce");
                    add("widget");
                    add("behavior");
                    add("frame");
                    add("build");
                    add("apply");
                    add("perform");
                    add("filter");
                }
            });
        }
    };



    static String currentGameCategory = generateRandomCategory();


    public static void setCustomWord(String word) {
        currentCustomWord = word;
    }

    public static void resetCustomWord() {
        currentCustomWord = "";
    }

    public static int getStreak() {
        return currentGameStreak;
    }

    public static void updateStreak(boolean won) {
        if (won) currentGameStreak++;
        else currentGameStreak = 0;
    }

    /**
     * This method get the next word in the category
     *
     * @return String next word
     */
    public static String getNextWord() {
        if (!currentCustomWord.isEmpty()) return currentCustomWord;
        if (!gameWordDataCache.containsKey(currentGameCategory))
            gameWordDataCache.put(currentGameCategory, new ArrayList<String>());
        Random rand = new Random();
        ArrayList<String> words = getCategoryWords(currentGameCategory);
        String word = words.get(rand.nextInt(words.size()));
        while (gameWordDataCache.get(currentGameCategory).contains(word)) {
            word = words.get(rand.nextInt(words.size()));
        }
        gameWordDataCache.get(currentGameCategory).add(word);
        return word;
    }

    /**
     * This method gets the words from the category
     *
     * @param category current category
     * @return ArrayList<String> list of words
     */
    public static ArrayList<String> getCategoryWords(String category) {
        ArrayList<String> names = new ArrayList<String>();
        for (String name : gameWordData.get(category)) {
            names.add(name);
        }
        return names;
    }

    /**
     * This method gets the categories from gameWordData map
     *
     * @return ArrayList<String> list of categories names
     */
    public static ArrayList<String> getCategoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (String name : gameWordData.keySet()) {
            names.add(name);
        }
        return names;
    }

    /**
     * This method gets available categories
     *
     * @return HashMap<String, Boolean> categories names
     */
    public static HashMap<String, Boolean> getCategoryList() {
        HashMap<String, Boolean> names = new HashMap<String, Boolean>();
        for (String name : gameWordData.keySet()) {
            names.put(name, isCategoryAvailable(name));
        }
        return names;
    }

    /**
     * This method gets the category in progress
     *
     * @return String current category
     */
    public static String getCurrentCategory() {
        if (!isCategoryAvailable(currentGameCategory)) {
            if (isAnyCategoryAvailable()) {
                String category = generateRandomCategory();
                currentGameCategory = category;
                return category;
            } else {
                currentGameCategory = "NO_CATEGORIES";
                return "NO_CATEGORIES";//Game Ended
            }
        }
        return currentGameCategory;
    }

    /**
     * This method picks a random category from categories list
     *
     * @return String category
     */
    public static String generateRandomCategory() {
        Random rand = new Random();
        ArrayList<String> categories = getCategoryNames();
        String category = categories.get(rand.nextInt(categories.size()));
        while (!isCategoryAvailable(category)) {
            category = categories.get(rand.nextInt(categories.size()));
        }
        return category;
    }

    public static void setCurrentCategory(String category) {
        if (!isCategoryAvailable(category)) return;
        currentGameCategory = category;
    }

    /**
     * This method checks if category is available
     *
     * @param category category to check it
     * @return boolean if available
     */
    public static boolean isCategoryAvailable(String category) {
        if (currentGameCategory == "NO_CATEGORIES") return false;
        if (gameWordData.containsKey(category)) {
            if (gameWordDataCache.containsKey(category)) {
                if (gameWordData.get(category).size() == gameWordDataCache.get(category).size()) {
                    return false;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * This method checks if any category available
     *
     * @return boolean if available
     */
    public static boolean isAnyCategoryAvailable() {
        if (currentGameCategory == "NO_CATEGORIES") return false;
        boolean result = false;
        for (String category : gameWordData.keySet()) {
            if (gameWordData.containsKey(category)) {
                if (gameWordDataCache.containsKey(category)) {
                    if (gameWordData.get(category).size() != gameWordDataCache.get(category).size()) {
                        result = true;
                    }
                } else {
                    result = true;
                }
            }
        }
        return result;
    }
}
