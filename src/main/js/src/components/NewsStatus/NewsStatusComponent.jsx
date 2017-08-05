import React from 'react';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';
import { removeDateFromDateTime } from './../../transform/dateTimeTransformers';

export default class NewsStatusComponent extends React.Component {

    render() {
        return this.renderCard();
    }

    renderCard() {
        return (
            <Card>
                <CardHeader className={'card-header'}>
                    <span>News Status</span>
                    {this.renderCardHeaderLastUpdated()}
                </CardHeader>
                <CardText className={'weatherContainer'}>
                    {this.renderListData()}
                </CardText>
            </Card>
        );
    }

    renderCardHeaderLastUpdated() {
        if (this.props.data && this.props.data.length > 0) {
            return (
                <span className={'card-header-last-checked'}>
                         last updated: {removeDateFromDateTime(this.props.data[0].lastUpdated)}
                 </span>
            );
        }
    }

    renderListData() {
        if (this.shouldRenderArticles()) {
            return this.props.data[0].articles.map((data, index) => {
                return (
                    <div className={'newsContentCard'}>
                        <Card key={index}>
                            <CardHeader
                                title={data.title}
                                subtitle={data.author}
                                style={{paddingRight: 0}}
                            />
                            <CardText>
                                <div
                                    key={index}>
                                    {data.description}
                                </div>
                                <a href={data.url}>More..</a>
                            </CardText>
                        </Card>
                    </div>
                );
            })
        }
    }

    shouldRenderArticles() {
        return this.props.data[0] ? true : false;
    }
};