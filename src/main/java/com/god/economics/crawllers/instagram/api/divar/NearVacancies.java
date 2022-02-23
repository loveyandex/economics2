//package com.god.economics.crawllers.instagram.api.divar;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.util.List;
//
//// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
///* ObjectMapper om = new ObjectMapper();
//Root root = om.readValue(myJsonString), Root.class); */
//public class NearVacancies {
//    public String title;
//    public String type;
//}
//
//class Items {
//    @JsonProperty("enum")
//    public List<String> enums;
//    public List<String> enumNames;
//    public String type;
//}
//
//class Vacancies {
//    public Items items;
//    public int minItems;
//    public String title;
//    public String type;
//    @JsonProperty("ui:options")
//    public UiOptions uiOptions;
//}
//
//class Properties2 {
//    @JsonProperty("near-vacancies")
//    public NearVacancies nearVacancies;
//    public Vacancies vacancies;
//    public Districts districts;
//}
//
//class Districts {
//    public boolean additionalProperties;
//    public Properties2 properties;
//    public List<String> required;
//    public String title;
//    public String type;
//    @JsonProperty("ui:field")
//    public String uiField;
//    @JsonProperty("ui:options")
//    public UiOptions uiOptions;
//    public Urischema urischema;
//    public Vacancies vacancies;
//}
//
//class JsonSchema {
//    public Properties  properties;
//    public String type;
//}
//
//class UiOptions {
//    @JsonProperty("active-internal-links")
//    public boolean activeInternalLinks;
//    @JsonProperty("display-text-format")
//    public String displayTextFormat;
//    public String icon;
//    @JsonProperty("is-advanced")
//    public boolean isAdvanced;
//    public String label;
//    @JsonProperty("parent-category-slug")
//    public String parentCategorySlug;
//    @JsonProperty("post-set-label")
//    public String postSetLabel;
//    public Data data;
//}
//
//public class Urischema {
//    public int order;
//}
//
//public class Child {
//    public String enum;
//    public String enumName;
//    public List<String> tags;
//}
//
//class Data {
//    public List<Child> children;
//    @JsonProperty("enum")
//
//    public String enum2;
//    public String enumName;
//    public String title;
//    public String image;
//    public List<WebImage> web_image;
//    public String description;
//    public boolean has_chat;
//    public String red_text;
//    public String normal_text;
//    public String token;
//    public Object image_overlay_tag;
//    public Object image_top_left_tag;
//    public int index;
//    @JsonProperty("postapi-version")
//    public int postapiVersion;
//    public String city;
//    public String district;
//    public String category;
//    public List<String> category_hierarchy;
//}
//
//class UiSchema {
//    public Districts districts;
//}
//
//class InputSuggestion {
//    public JsonSchema json_schema;
//    public UiSchema ui_schema;
//}
//
//class MetaRobots {
//    public boolean follow;
//    public boolean index;
//}
//
//class BreadCrumb {
//    public String name;
//    public String url;
//}
//
//class SeoDetails {
//    public String title;
//    public String description;
//    public String headline;
//    public MetaRobots meta_robots;
//    public List<BreadCrumb> bread_crumbs;
//    public String next;
//    public String web_url;
//    public String android_url;
//    public String canonical;
//    public String prev;
//    public List<Object> meta_tags;
//}
//
//class WebImage {
//    public String src;
//    public String type;
//}
//
//class WidgetList {
//    public String widget_type;
//    public Data data;
//}
//
//class PostList {
//    public String widget_type;
//    public Data data;
//}
//
//class WebWidgets {
//    public List<Object> toolbox;
//    public List<PostList> post_list;
//}
//
//class Tab {
//    public int type;
//    public String text;
//}
//
//class TabList {
//    public int current_tab;
//    public List<Tab> tabs;
//}
//
//class Root {
//    public InputSuggestion input_suggestion;
//    public String title;
//    public String subtitle;
//    public SeoDetails seo_details;
//    public Object internal_link_sections;
//    public List<WidgetList> widget_list;
//    public long last_post_date;
//    public long first_post_date;
//    public WebWidgets web_widgets;
//    public List<Object> banners;
//    public TabList tab_list;
//    public List<Object> suggestion_list;
//}
//
